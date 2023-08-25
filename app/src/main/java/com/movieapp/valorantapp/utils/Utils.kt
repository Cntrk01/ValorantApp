package com.movieapp.valorantapp.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.movieapp.valorantapp.R
import androidx.databinding.BindingAdapter

fun ImageView.gorselIndır(url : String?, placeHolder : CircularProgressDrawable)
{
    val option= RequestOptions().placeholder(placeHolder).error(R.mipmap.ic_launcher)//2)Resim yüklenirken kullanıcı karşısına dönen bir simge çıkartıcaz boş ekran yerine.Bunun için place holder lazım
    Glide.with(context).setDefaultRequestOptions(option).load(url).into(this) //1)Glide kullanacagımızı belirttikk.Bu satırı yapmak için diğer işlemleri gerçekleştirdik.
}

fun placeHolderYap(context : Context) : CircularProgressDrawable //3)Place holder sınıfını yazıcaz şimdi
{
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f //Dairenin kalınlığını gösteriyor
        centerRadius=40f //10 ile 40 arasında olsun çok büyük olabilr
        start() //BUNLAR BİTTİ ŞİMDİ ADAPTERE GİDİP RESİM HOLDER EKLEYECEĞİZ.
    }
}

@BindingAdapter("android:downLoadUrl")
fun downUrl(view: ImageView,url:String?){
    view.gorselIndır(url, placeHolderYap(view.context))
}