package com.example.ladm_u4_practica2_servisiossms

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    val siPermiso = 1
    val siPermisoReceiver = 2
    val siPermisoLectura = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//--------------------------Permisos-----------------------------------------

        //Recibir mensajes
        if(ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.RECEIVE_SMS),siPermisoReceiver)
        }
        //Leer mensajes
        if(ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.READ_SMS),siPermisoLectura)
        }
        //Enviar mensajes
        if(ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.SEND_SMS), siPermiso
            )
        }
    }
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==siPermiso){
        }

        if(requestCode==siPermisoReceiver){
        }

        if(requestCode==siPermisoLectura){
        }
    }//onRequestPermission

}