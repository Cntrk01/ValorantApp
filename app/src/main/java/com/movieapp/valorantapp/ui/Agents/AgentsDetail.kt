package com.movieapp.valorantapp.ui.Agents

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.transition.Slide
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.models.SlideModel
import com.movieapp.valorantapp.R
import com.movieapp.valorantapp.databinding.FragmentAgentsBinding
import com.movieapp.valorantapp.databinding.FragmentAgentsDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.lang.Runnable
import java.text.DecimalFormat


@AndroidEntryPoint
class AgentsDetail : Fragment() {

    var bool=false

    private var _binding: FragmentAgentsDetailBinding? = null
    private val binding get() = _binding!!

    private val args: AgentsDetailArgs by navArgs()

    private val arrayList = arrayListOf<SlideModel>()

    val mediaPlayer = MediaPlayer()

    private var updateJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgentsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = args.agents.displayName
        setSliderImage()
        setData()

        binding.startVoice.setOnClickListener {
            listenMedia()
        }
    }

    private fun setSliderImage(){
        arrayList.add(SlideModel(args.agents.background!!,"Poster"))
        arrayList.add(SlideModel(args.agents.bustPortrait!!,"Portrait"))
        arrayList.add(SlideModel(args.agents.displayIcon!!,"Icon"))
//        arrayList.add(SlideModel(args.agents.displayIconSmall!!,""))
//        arrayList.add(SlideModel(args.agents.fullPortrait!!,""))
//        arrayList.add(SlideModel(args.agents.fullPortraitV2!!,""))
//        arrayList.add(SlideModel(args.agents.killfeedPortrait!!,""))
        binding.imageSlider.setImageList(arrayList)
    }

    private fun setData(){
        binding.agentsDispName.text=args.agents.displayName
        binding.agentsDevName.text=args.agents.developerName
        binding.agentsDesc.text=args.agents.description
        binding.agentsUid.text=args.agents.uuid

        //binding.agentsCharacterTags.text=args.agents.characterTags.toString() boş dönüyor

        //Renkler Hexadesimal geldiği için parseColor kullanarak renklere çevirme işlemini yapmamız gerekiyor
        //forEachIndexed metodu, her bir renk için bir indeks değeri sağlar, böylece doğru View'e uygun rengi atayabilirsiniz.
        //Foreach ile forEachIndexed metodu arasındaki vark  forEachIndexed da indeks var yani
        // 0. değeri alırken onun 0. değer olduğunu biliyoruz.Fakat forEachde indeks değerlerini alırken indeksleri bilemeyiz!!
        args.agents.backgroundGradientColors?.forEachIndexed { index, color ->
            when (index) {
                0 -> binding.color1.setBackgroundColor(Color.parseColor("#$color"))
                1 -> binding.color2.setBackgroundColor(Color.parseColor("#$color"))
                2 -> binding.color3.setBackgroundColor(Color.parseColor("#$color"))
                3 -> binding.color4.setBackgroundColor(Color.parseColor("#$color"))
            }
        }

        //Örneği burada
        /*args.agents.abilities?.forEachIndexed { index, ability ->
            Log.e("ability","$index , $ability")
        }*/

        //Abilities kısımı
        args.agents.abilities?.forEach {
            binding.abilityDescription.text= it.description
            Glide.with(requireView()).load(it.displayIcon).into(binding.abilitydisplayIcon)
            binding.abilitySlot.text=it.slot
            binding.abilityDisplayName.text=it.displayName

        }

        //Rol kısımı
        binding.roleUid.text=args.agents.role?.uuid
        binding.roleDisplayName.text=args.agents.role?.displayName
        Glide.with(requireView()).load(args.agents.role?.displayIcon).into(binding.roleIcon)
        binding.roleDescription.text=args.agents.role?.description

        //Ses uzunlugu
        val number = args.agents.voiceLine?.maxDuration
        val decimalFormat = DecimalFormat("#.##")
        val formattedNumber = decimalFormat.format(number)
        binding.voiceTime.text=formattedNumber.toString()
    }


    private fun listenMedia(){
        var media=""

        args.agents.voiceLine?.mediaList?.forEach {itt->
            media=itt.wave
        }

        mediaPlayer.setDataSource(media)

        mediaPlayer.setOnPreparedListener { player ->

            // Ses dosyası hazır olduğunda oynatmayı başlat
            player.start()
            // İlerleme çubuğunun maksimum değerini ses dosyasının süresine ayarla
            binding.progressSeekBar.max = mediaPlayer.duration
            // Süreyi güncellemek için zamanlayıcı başlat
            updateProgressBar()
        }

        mediaPlayer.setOnCompletionListener { player ->
            // Ses dosyası tamamlandığında ilerleme çubuğunu sıfırla

            binding.progressSeekBar.progress = 0
            binding.voiceTime.text="0.0"
            //Bitince seek barı başa alıyor
            mediaPlayer.seekTo(0)

            // Medya oynatıcısını serbest bırakıyor böylelikle butona tıklayınca tekrar çalmasını sağlıyor.Fakat bu çökme olmasına sebeb oluyor
            //mediaPlayer.release()

        }
        mediaPlayer.prepareAsync()
    }



    private fun updateProgressBar() {
        // Süre güncelleme aralığı (örneğin, her 0.1 saniyede bir) 1000L den 10L yaptım daha akıcı oldu
        val intervalMillis = 10L

        updateJob = CoroutineScope(Dispatchers.Main).launch {
            while (isActive) {
                // MediaPlayer'ın mevcut ilerleme değerini al
                val currentPosition = if (mediaPlayer.isPlaying) mediaPlayer.currentPosition else 0
                // İlerleme çubuğunu güncelle
                binding.progressSeekBar.progress = currentPosition
                // Süre göstergesini güncelle
                val formattedTime = formatTime(currentPosition)
                binding.voiceTime.text = formattedTime

                delay(intervalMillis)
            }
        }
    }
    // İlerleme çubuğunu durdurmak için:
    private fun stopUpdateProgressBar() {
        updateJob?.cancel()
    }


    private fun formatTime(milliseconds: Int): String {
        val totalSeconds = milliseconds / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
}