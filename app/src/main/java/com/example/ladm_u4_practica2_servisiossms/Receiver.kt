package com.example.ladm_u4_practica2_servisiossms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteException
import android.os.Build
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.widget.Toast

class Receiver :BroadcastReceiver(){
    var mensaje2 = ""
    override fun onReceive(context: Context, intent: Intent) {
        val extras = intent.extras

        if(extras != null){
            var sms=extras.get("pdus") as Array<Any>
            for(i in sms.indices){
                var formato = extras.getString("format")
                var smsMensaje = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    SmsMessage.createFromPdu(sms[i] as ByteArray, formato)
                } else {
                    SmsMessage.createFromPdu(sms[i] as ByteArray)
                }//if-else
                var celularOrigen = smsMensaje.originatingAddress
                var contenidoSMS = smsMensaje.messageBody.toString()
                var mensaje = contenidoSMS.split(" ")
                Toast.makeText(context, "Entró Mensaje ${contenidoSMS}"+"MENSAJE: ${mensaje[0]}",Toast.LENGTH_LONG).show()
                if(mensaje.size != 2){
                    SmsManager.getDefault().sendTextMessage(celularOrigen,null,"Por favor envie: COMIDA tacos, enviar la palabra COMIDA seguida de la comida que desea saber su existencia",null,null)
                    return
                }
                else if (!mensaje[0].equals("COMIDA")) {
                    SmsManager.getDefault().sendTextMessage(
                            celularOrigen,
                            null,
                            "Por favor, asegurese de enviar el mensaje de la manera correcta. Ejemplo: COMIDA tacos",
                            null,
                            null
                    )
                }else {
                    try {

                        val cursor = BaseDatos(context, "Comida", null, 1)
                                .readableDatabase
                                .rawQuery("SELECT Lugar FROM Comida WHERE Platillo = '${mensaje[1]}'", null)
                        if (cursor.moveToFirst()) {
                            do {
                                mensaje2 = "La Comida que pediste esta disponible en : " + cursor.getString(0)
                                SmsManager.getDefault()
                                        .sendTextMessage(
                                        celularOrigen,
                                        null,
                                        mensaje2,
                                        null,
                                        null
                                )
                            }while (cursor.moveToNext())
                        } else {
                            SmsManager.getDefault().sendTextMessage(
                                    celularOrigen, null, "no hay Comida de la que pidió", null, null
                            )
                        }

                    } catch (e: SQLiteException) {
                        Toast.makeText(context, e.message.toString(), Toast.LENGTH_LONG).show()
                    }//try-catch
                }

            }
        }

    }

}