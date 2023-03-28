package com.example.conversormonedas;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Double> resultado=null;

    private  MutableLiveData<String> res=null;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }

    public LiveData<Double> getCambio(){
        if(resultado==null){
            this.resultado=new MutableLiveData<>();
        }
        return resultado;
    }
    public LiveData<String> getResultado(){
        if(res==null){
            this.res=new MutableLiveData<>();
        }
        return res;
    }
    public void Cambio(String camE1,String camD1,char cambio){
        try{
            Double c=-1.0;
            if(cambio=='D'){
                Double camb=Double.parseDouble(camD1);
                c =camb*0.92;
                res.setValue("Euros");
            }else if(cambio=='E') {
                double camb2=Double.parseDouble(camE1);
                c=camb2*1.08;
                res.setValue("Dolares");
            }
            resultado.setValue(c);
        }catch (Exception ex){
            Toast.makeText(context,"Usted no Ingreso un valor",Toast.LENGTH_LONG).show();
        }

    }

}
