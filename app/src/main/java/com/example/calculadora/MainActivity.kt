package com.example.calculadora

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
    private var componentA = " "
    private var componentB = " "
    private var operation= " " //operación a realizar
    private var result = " " //resultado
    private var register= " " //registro de las operaciones realizadas
    private var isResult=false
    private var isFunction=false



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
        outState.putString("textResult", result) //guardo el estado del resultado
    }

    override fun onClick(v: View?) {

        //Diferenciar qué botón he pulsado
        when(v?.id){ // si la vista es nula que no lo haga

            //Defino la funcionalidad cuando se pulsa cada uno de los botones
            binding.btnAC.id->{
                result=clearResult()
                register=clearRegister()
            }

            binding.btnDecimal.id->{

                result=checkResult(isResult, result)

                if(!(result.split(".").size==2)) { //compruebo si el número ya es decimal
                    result += "."
                }

                isResult=false
            }

            binding.btnChange.id->{

                if(result.get(0)!= '-'){
                    result='-'+result
                }
                else{
                    result=result.replace("-","")
                }


            }

            //Números
                binding.btn0.id->{
                    result=checkResult(isResult, result)
                    register=checkRegister(isResult, register)
                    result+="0"
                    isResult=false

                }

                binding.btn1.id->{
                    result=checkResult(isResult, result)
                    register=checkRegister(isResult, register)
                    result+="1"
                    isResult=false
                }

                binding.btn2.id->{
                    result=checkResult(isResult, result)
                    register=checkRegister(isResult, register)
                    result+="2"
                    isResult=false

                }

                binding.btn3.id->{
                    result=checkResult(isResult, result)
                    register=checkRegister(isResult, register)
                    result+="3"
                    isResult=false

                }

                binding.btn4.id->{
                    result=checkResult(isResult, result)
                    register=checkRegister(isResult, register)
                    result+="4"
                    isResult=false

                }

                binding.btn5.id->{
                    result=checkResult(isResult, result)
                    register=checkRegister(isResult, register)
                    result+="5"
                    isResult=false

                }

                binding.btn6.id->{
                    result=checkResult(isResult, result)
                    register=checkRegister(isResult, register)
                    result+="6"
                    isResult=false

                }

                binding.btn7.id->{
                    result=checkResult(isResult, result)
                    register=checkRegister(isResult, register)
                    result+="7"
                    isResult=false

                }

                binding.btn8.id->{
                    result=checkResult(isResult, result)
                    register=checkRegister(isResult, register)
                    result+="8"
                    isResult=false

                }

                binding.btn9.id->{
                    result=checkResult(isResult, result)
                    register=checkRegister(isResult, register)
                    result+="9"
                    isResult=false

                }

                binding.btnPI?.id->{
                    result=checkResult(isResult, result)
                    result=Math.PI.toString()
                    isResult=true
                }

                binding.btnE?.id->{
                    result=checkResult(isResult, result)
                    result=Math.E.toString()
                    isResult=true
                }

                binding.btnRand?.id->{
                    result=checkResult(isResult, result)
                    result=Math.random().toString()
                    isResult=true
                }

            //Operaciones

                binding.btnAdd.id->{
                    componentA = result
                    operation="+"
                    result=clearResult()
                    register+=makeRegister(componentA, operation, isResult)
                    isResult=false
                }

                binding.btnDiff.id->{
                    componentA=result
                    operation="-"
                    result=clearResult()
                    register+=makeRegister(componentA, operation, isResult)
                    isResult=false
                }

                binding.btnMultiply.id-> {
                    componentA = result
                    operation = "x"
                    result=clearResult()
                    register+=makeRegister(componentA, operation, isResult)
                    isResult=false
                }

                binding.btnDivide.id->{
                    componentA=result
                    operation="/"
                    result=clearResult()
                    register+=makeRegister(componentA, operation, isResult)
                    isResult=false
                }

                binding.btnXY?.id->{
                    componentA=result
                    operation="xy"
                    result=clearResult()

                }

            //Funciones

                binding.btnPercent.id->{
                    componentA=result
                    operation="%"

                    register+=makeRegister(componentA, "%", isResult) //último operando
                    result=makeFunction(componentA, operation) //hago la operación
                    register+=clearNumber(result) //añado el resultado

                    isResult=true
                    isFunction=true

                }
            
                binding.btnSen?.id->{
                    componentA=result
                    operation="sen"
                    result=makeFunction(componentA, operation)
                    isResult=true
                }

                binding.btnCos?.id->{
                    componentA=result
                    operation="cos"
                    result=makeFunction(componentA, operation)
                    isResult=true
                }

                binding.btnTan?.id->{
                    componentA=result
                    operation="tan"
                    result=makeFunction(componentA, operation)
                    isResult=true
                }

                binding.btnArcsen?.id->{
                    componentA=result
                    operation="arcsen"
                    result=makeFunction(componentA, operation)
                    isResult=true
                }

                binding.btnArccos?.id->{
                    componentA=result
                    operation="arccos"
                    result=makeFunction(componentA, operation)
                    isResult=true
                }

                binding.btnArctan?.id->{
                    componentA=result
                    operation="arctan"
                    result=makeFunction(componentA, operation)
                    isResult=true
                }

                binding.btnX2?.id->{
                    componentA=result
                    operation="x2"
                    result=makeFunction(componentA, operation)
                    isResult=true
                }

                binding.btnX3?.id->{
                    componentA=result
                    operation="x3"
                    result=makeFunction(componentA, operation)
                    isResult=true
                }

                binding.btnSquareroot?.id->{
                    componentA=result
                    operation="squareRoot"
                    result=makeFunction(componentA, operation)
                    isResult=true
                }

                binding.btnLog?.id->{
                    componentA=result
                    operation="log"
                    result=makeFunction(componentA, operation)
                    isResult=true
                }

                binding.btn1x?.id->{
                    componentA=result
                    operation="1/x"
                    result=makeFunction(componentA, operation)
                    isResult=true
                }


            //Resultado
                binding.btnResult.id->{
                    if(!isResult) { //controlo que cuando se ha hecho una operación y ya tengo el resultado, si se vuelve a pulsar el el botón de "=", la pantalla se quede vacía
                        componentB = result

                        register+=makeRegister(componentB, "=", isResult) //último operando
                        result = makeOperation(componentA, componentB, operation) //hago la operación
                        register+=clearNumber(result) //añado el resultado

                        isResult = true
                    }
                }
        }


        //Imprimir el resultado en pantalla
        result = clearNumber(result)

        binding.textResult.text = result
        binding.textRegister?.text=register

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
    private fun clearResult() : String{
        return "0"
    }

    //Método para resetear el registro
    private fun clearRegister() : String{
        return " "
    }

    //Método para hacer la operación correspondiente
    private fun makeOperation(componentA : String, componentB: String, operation: String) : String{

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

    }

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
}