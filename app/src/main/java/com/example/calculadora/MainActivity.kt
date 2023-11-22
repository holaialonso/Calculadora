package com.example.calculadora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.View.OnClickListener
import com.example.calculadora.databinding.ActivityMainBinding
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class MainActivity : AppCompatActivity(), OnClickListener{

    //Fichero -> me traigo el layout (activity_main.xml)
    private lateinit var binding : ActivityMainBinding
    /*private var componentA = " "
    private var componentB = " "
    private var operation= " " //operación a realizar
    private var result = " " //resultado
    private var register= " " //registro de las operaciones realizadas
    private var isResult=false
    private var isFunction=false*/


    private var result="" //resultado
    private var operation="" //operación a realizar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Rellenamos el layout
        binding = ActivityMainBinding.inflate(layoutInflater)

        //Pego el archivo que acabo de rellenar
        setContentView(binding.root)

        //Recupero el estado -> del resultado
        result= savedInstanceState?.getString("textResult") ?: "0" //recupero
        binding.textResult.text=result //muestro

        //Funcionalidad de la calculadora
        //Números
            binding.btnAC.setOnClickListener(this)
            binding.btnDecimal.setOnClickListener(this)
            binding.btnChange.setOnClickListener(this)
            binding.btn0.setOnClickListener(this)
            binding.btn1.setOnClickListener(this)
            binding.btn2.setOnClickListener(this)
            binding.btn3.setOnClickListener(this)
            binding.btn4.setOnClickListener(this)
            binding.btn5.setOnClickListener(this)
            binding.btn6.setOnClickListener(this)
            binding.btn7.setOnClickListener(this)
            binding.btn8.setOnClickListener(this)
            binding.btn9.setOnClickListener(this)
            binding.btnPI?.setOnClickListener(this)
            binding.btnE?.setOnClickListener(this)
            binding.btnRand?.setOnClickListener(this)

        //Operaciones
            binding.btnAdd.setOnClickListener(this)
            binding.btnDiff.setOnClickListener(this)
            binding.btnMultiply.setOnClickListener(this)
            binding.btnDivide.setOnClickListener(this)
            binding.btnXY?.setOnClickListener(this)
            binding.btn1x?.setOnClickListener(this)

        //Funciones
            binding.btnPercent.setOnClickListener(this)
            binding.btnSen?.setOnClickListener(this)
            binding.btnCos?.setOnClickListener(this)
            binding.btnTan?.setOnClickListener(this)
            binding.btnArcsen?.setOnClickListener(this)
            binding.btnArccos?.setOnClickListener(this)
            binding.btnArctan?.setOnClickListener(this)
            binding.btnX2?.setOnClickListener(this)
            binding.btnX3?.setOnClickListener(this)
            binding.btnSquareroot?.setOnClickListener(this)
            binding.btnLog?.setOnClickListener(this)

        //Resultado
            binding.btnResult.setOnClickListener(this)


    }

    //Método para guardar el estado
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("textResult", binding.textResult.getText().toString()) //guardo el estado del resultado
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {

        //Diferenciar qué botón he pulsado
        when(v?.id){ // si la vista es nula que no lo haga

            //Defino la funcionalidad cuando se pulsa cada uno de los botones
            binding.btnAC.id->{
                clearResult()
                result=""
                operation=""

            }

            binding.btnDecimal.id->{
                if(!(binding.textResult.getText().toString().split(".").size==2)) { //compruebo si el número ya es decimal
                    printNumber(binding.textResult.getText().toString()+'.')
                }
            }

            binding.btnChange.id->{
                if(binding.textResult.getText().toString().get(0)!= '-'){ //Compruebo el signo del número
                    printNumber('-'+binding.textResult.getText().toString())
                }
                else{
                    printNumber(binding.textResult.getText().toString().replace("-", ""))
                }
            }

            //Números
                binding.btn0.id->{
                    printNumber(binding.textResult.getText().toString()+'0')
                }

                binding.btn1.id->{
                    printNumber(binding.textResult.getText().toString()+'1')
                }

                binding.btn2.id->{
                    printNumber(binding.textResult.getText().toString()+'2')
                }

                binding.btn3.id->{
                    printNumber(binding.textResult.getText().toString()+'3')
                }

                binding.btn4.id->{
                    printNumber(binding.textResult.getText().toString()+'4')
                }

                binding.btn5.id->{
                    printNumber(binding.textResult.getText().toString()+'5')
                }

                binding.btn6.id->{
                    printNumber(binding.textResult.getText().toString()+'6')
                }

                binding.btn7.id->{
                    printNumber(binding.textResult.getText().toString()+'7')
                }

                binding.btn8.id->{
                    printNumber(binding.textResult.getText().toString()+'8')
                }

                binding.btn9.id->{
                    printNumber(binding.textResult.getText().toString()+'9')
                }

                binding.btnPI?.id->{
                   printNumber(Math.PI.toString())
                }

                binding.btnE?.id->{
                    printNumber(Math.E.toString())
                }

                binding.btnRand?.id->{
                   printNumber(Math.random().toString())
                }

            //Operaciones

                binding.btnAdd.id->{

                    result=makeOperation(result, operation, false);
                    operation="+";
                    clearFieldResult();
                }

                binding.btnDiff.id->{

                    result=makeOperation(result, operation, false);
                    operation="-";
                    clearFieldResult();
                }

                binding.btnMultiply.id-> {

                    result=makeOperation(result, operation, false);
                    operation="*";
                    clearFieldResult();
                }

                binding.btnDivide.id->{

                    result=makeOperation(result, operation, false);
                    operation="/";
                    clearFieldResult();

                }

                binding.btnXY?.id->{
                    result=makeOperation(result, operation, false);
                    operation="xy"
                    clearFieldResult();

                }

            //Funciones

                binding.btnPercent.id->{
                    if(operation!="") {
                        result=makeOperation(result, operation, false)
                    }
                    result=makeOperation(result, "%", true)
                    printNumber(result)

                    result=""
                    operation=""

                }
            
                binding.btnSen?.id->{
                    if(operation!="") {
                        result=makeOperation(result, operation, false)
                    }
                    result=makeOperation(result, "sen", true)
                    printNumber(result)

                    result=""
                    operation=""
                }

                binding.btnCos?.id->{
                    if(operation!="") {
                        result=makeOperation(result, operation, false)
                    }
                    result=makeOperation(result, "cos", true)
                    printNumber(result)

                    result=""
                    operation=""
                }

                binding.btnTan?.id->{
                    if(operation!="") {
                        result=makeOperation(result, operation, false)
                    }
                    result=makeOperation(result, "tan", true)
                    printNumber(result)

                    result=""
                    operation=""
                }

                binding.btnArcsen?.id->{
                    if(operation!="") {
                        result=makeOperation(result, operation, false)
                    }
                    result=makeOperation(result, "arcsen", true)
                    printNumber(result)

                    result=""
                    operation=""
                }

                binding.btnArccos?.id->{
                    if(operation!="") {
                        result=makeOperation(result, operation, false)
                    }
                    result=makeOperation(result, "arccos", true)
                    printNumber(result)

                    result=""
                    operation=""
                }

                binding.btnArctan?.id->{
                    if(operation!="") {
                        result=makeOperation(result, operation, false)
                    }
                    result=makeOperation(result, "arctan", true)
                    printNumber(result)

                    result=""
                    operation=""
                }

                binding.btnX2?.id->{
                    if(operation!="") {
                        result=makeOperation(result, operation, false)
                    }
                    result=makeOperation(result, "x2", true)
                    printNumber(result)

                    result=""
                    operation=""
                }

                binding.btnX3?.id->{
                    if(operation!="") {
                        result=makeOperation(result, operation, false)
                    }
                    result=makeOperation(result, "x3", true)
                    printNumber(result)

                    result=""
                    operation=""
                }

                binding.btnSquareroot?.id->{
                    if(operation!="") {
                        result=makeOperation(result, operation, false)
                    }
                    result=makeOperation(result, "squareRoot", true)
                    printNumber(result)

                    result=""
                    operation=""
                }

                binding.btnLog?.id->{
                    if(operation!="") {
                        result=makeOperation(result, operation, false)
                    }
                    result=makeOperation(result, "log", true)
                    printNumber(result)

                    result=""
                    operation=""
                }

                binding.btn1x?.id->{
                    if(operation!="") {
                        result=makeOperation(result, operation, false)
                    }
                    result=makeOperation(result, "1/x", true)
                    printNumber(result)

                    result=""
                    operation=""
                }


            //Resultado
                binding.btnResult.id->{
                    if(operation!="") {
                        result = makeOperation(result, operation, false)
                    }
                    printNumber(result)
                    result=""
                    operation=""
                }
        }


        //Imprimir el resultado en pantalla
        /*result = clearNumber(result)

        binding.textResult.text = result
        binding.textRegister?.text=register*/

    }

    //Método para formatear el número de forma correcta: quitamos los ceros a la izquierda, en caso de que el número sea entero el ".0" del final, etc.
    private fun clearNumber(number: String) : String{

        var aux=" "

        if(isInt(number)){ //Si el número es entero

            if (number.get(0) == '-') { //En caso de números negativos
                aux= '-' + number.replace("-", "").replace(Regex("^0+(?!$)"), "");
            } else {

                //Le quito los ceros a la izquierda
                aux= number.replace(Regex("^0+(?!$)"), "");

            }

            //Le quito el ".0" del final
            aux=aux.replace(".0", "")
        }
        else{ //Si el número es decimal -> lo retorno tal cuál

            aux=number
        }

        return aux

    }

        //Método para comprobar si un número es entero
        private fun isInt (number : String) : Boolean{

            var aux=number.split(".")
            var isInt=true

            if(aux.size>1){

                if(aux[1]!= "0"){
                   isInt=false
                }

            }

            return isInt

        }

    //Método para resetear la calculadora
    private fun clearResult(){
        binding.textResult.text="0"
    }

    //Método para resetear el registro
    private fun clearRegister(){
       // binding.textRegister.text="0"
    }

    //Método para hacer la operación correspondiente
    /*private fun makeOperation(componentA : String, componentB: String, operation: String) : String{

        var aux=0.0

        when(operation){

            "+"->{
                aux=componentA.toDouble()+componentB.toDouble()
            }

            "-"->{
                aux=componentA.toDouble()-componentB.toDouble()
            }

            "x"->{
                aux=componentA.toDouble()*componentB.toDouble()
            }

            "/"->{
                aux=componentA.toDouble()/componentB.toDouble()
            }

            "xy"->{
                aux=Math.pow(componentA.toDouble(), componentB.toDouble())
            }
        }

        return aux.toString()

    }*/

    //Método para hacer una operación con las funciones
    private fun makeFunction(componentA : String, operation : String) : String{

        var aux=0.0

        when(operation){

            "%"->{

                aux=componentA.toDouble()/100

            }

            "sen"->{

                //Para las funciones trigonométricas los ángulos se expresan en radianes, no en grados
                aux= sin(Math.toRadians(componentA.toDouble()))

            }

            "cos"->{
                aux= cos(Math.toRadians(componentA.toDouble()))
            }

            "tan"->{
                aux= tan(Math.toRadians(componentA.toDouble()))
            }

            "arcsen"->{
                aux= asin(Math.toRadians(componentA.toDouble()))
            }

            "arccos"->{
                aux= acos(Math.toRadians(componentA.toDouble()))
            }

            "arctan"->{
                aux= atan(Math.toRadians(componentA.toDouble()))
            }

            "x2"->{
                aux=Math.pow(componentA.toDouble(), 2.0)
            }

            "x3"->{
                aux=Math.pow(componentA.toDouble(), 3.0)
            }

            "squareRoot"->{
                aux=Math.sqrt(componentA.toDouble())
            }

            "log"->{
                aux=Math.log(componentA.toDouble())
            }

            "1/x"->{
                aux=(1/componentA.toDouble())
            }
        }
        return aux.toString()
    }

    //Método con el que compruebo si el el resultado lo tengo que resetear antes de meter el siguiente valor (para que cuando hagamos una operación y tengamos el resultado no se pueda modificar)
    private fun checkResult(isResult: Boolean, result : String) : String{

        return if(isResult) "0" else result
    }

    private fun checkRegister(isResult: Boolean, register: String) : String{

        return if(isResult) " " else register

    }

    //Método para montar el registro de las operaciones
    private fun makeRegister(component: String, operation: String, isResult : Boolean) : String{

        var aux=if(component.get(0)=='-') "("+component+")" else component

        return if(!isResult) aux+operation else operation
    }


    //---------

    //Método para imprimir un número en el campo textResult
    private fun printNumber(number: String){
        binding.textResult.text=clearNumber(number)
    }


    //Método para limpiar el textResult
    private fun clearFieldResult(){
        binding.textResult.text="0"
    }

    //Método para resolver las operaciones matemáticas de la calculadora
    private fun makeOperation(result: String, operation: String, isFunction: Boolean) : String{

        var aux=0.0
        var componentA=if(result.length==0) binding.textResult.getText().toString() else result
        var componentB=if((result.length==0)||(isFunction)) "" else binding.textResult.getText().toString()


        if(componentB.length!=0){
            when(operation){
                "+"->{
                    aux=componentA.toDouble()+componentB.toDouble()
                }

                "-"->{
                    aux=componentA.toDouble()-componentB.toDouble()
                }

                "*"->{
                    aux=componentA.toDouble()*componentB.toDouble()
                }

                "/"->{
                    aux=componentA.toDouble()/componentB.toDouble()
                }

                "xy"->{
                    aux=Math.pow(componentA.toDouble(), componentB.toDouble())
                }

            }
        }
        else{

            if(isFunction){

                when(operation){

                    "%"->{
                        aux=componentA.toDouble()/100
                    }

                    "sen"->{

                        //Para las funciones trigonométricas los ángulos se expresan en radianes, no en grados
                        aux= sin(Math.toRadians(componentA.toDouble()))

                    }

                    "cos"->{
                        aux= cos(Math.toRadians(componentA.toDouble()))
                    }

                    "tan"->{
                        aux= tan(Math.toRadians(componentA.toDouble()))
                    }

                    "arcsen"->{
                        aux= asin(Math.toRadians(componentA.toDouble()))
                    }

                    "arccos"->{
                        aux= acos(Math.toRadians(componentA.toDouble()))
                    }

                    "arctan"->{
                        aux= atan(Math.toRadians(componentA.toDouble()))
                    }

                    "x2"->{
                        aux=Math.pow(componentA.toDouble(), 2.0)
                    }

                    "x3"->{
                        aux=Math.pow(componentA.toDouble(), 3.0)
                    }

                    "squareRoot"->{
                        aux=Math.sqrt(componentA.toDouble())
                    }

                    "log"->{
                        aux=Math.log(componentA.toDouble())
                    }

                    "1/x"->{
                        aux=(1/componentA.toDouble())
                    }
                }
            }
            else{
                aux=componentA.toDouble()
            }
        }

        return aux.toString()

        /*
            Double aux=0.0;
			String componentA="";
			String componentB="";

			componentA=(result=="") ? fieldResult.getText() : result;
			componentB=((result=="")||(isFunction)) ? "" : fieldResult.getText();

			if(componentB!="") {
				switch(operation) {

					case "+":
						aux=Double.parseDouble(componentA)+Double.parseDouble(componentB);
					break;

					case "-":
						aux=Double.parseDouble(componentA)-Double.parseDouble(componentB);
					break;

					case "*":
						aux=Double.parseDouble(componentA)*Double.parseDouble(componentB);
					break;

					case "/":
						aux=Double.parseDouble(componentA)/Double.parseDouble(componentB);
					break;
				}
			}
			else {


				if(operation=="%") {

					aux=Double.parseDouble(componentA)/100;

				}
				else {
					aux=Double.parseDouble(componentA);
				}
			}



			//Retorno el resultado	-> limitando los decimales a 4
			return clearNumber(Double.toString(makeRound(aux,4)));
         */

    }
}

