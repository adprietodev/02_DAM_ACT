import { useState } from "react";
import { Text, View, TouchableOpacity, StyleSheet } from "react-native";

export default function App() {
  //Array de objetos donde ponemos toda la información acerca de cada botón
  const btns = [
    [
      {
        id: 0,
        btn: "sen",
        fun: () => {
          setText(Math.sin(rad()).toString().replace(".", ","));
          setStartNum("true");
        },
        stateStart: "true",
      },
      {
        id: 1,
        btn: "cos",
        fun: () => {
          setText(Math.cos(rad()).toString().replace(".", ","));
          setStartNum("true");
        },
      },
      {
        id: 2,
        btn: "tan",
        fun: () => {
          setText(Math.tan(rad()).toString().replace(".", ","));
          setStartNum("true");
        },
      },
      {
        id: 3,
        btn: "deg",
        fun: () => {
          setText(deg().toString().replace(".", ","));
          setStartNum("true");
        },
      },
    ],
    [
      {
        id: 4,
        btn: "ln",
        fun: () => {
          setText(Math.log(checkFloat()).toString().replace(".", ","));
          setStartNum("true");
        },
      },
      {
        id: 5,
        btn: "log",
        fun: () => {
          setText(Math.log10(checkFloat()).toString().replace(".", ","));
          setStartNum("true");
        },
      },
      {
        id: 6,
        btn: "\u03C0",
        fun: () => {
          setText(Math.PI.toString().replace(".", ","));
          setStartNum("true");
        },
      },
      {
        id: 7,
        btn: "rad",
        fun: () => {
          setText(rad().toString().replace(".", ","));
          setStartNum("true");
        },
      },
    ],
    [
      {
        id: 8,
        btn: "1/X",
        fun: () => {
          setText((1 / checkFloat()).toString().replace(".", ","));
          setStartNum("true");
        },
      },
      {
        id: 9,
        btn: "!",
        fun: () => {
          setText(fact(parseInt(checkFloat())).toString().replace(".", ","));
          setStartNum("true");
        },
      },
      {
        id: 10,
        btn: "√",
        fun: () => {
          setText(Math.sqrt(checkFloat()).toString().replace(".", ","));
          setStartNum("true");
        },
      },
      {
        id: 11,
        btn: "/",
        fun: () => saveOp("/"),
      },
    ],
    [
      {
        id: 12,
        btn: 7,
        fun: () => addNum("7"),
      },
      {
        id: 13,
        btn: 8,
        fun: () => addNum("8"),
      },
      {
        id: 14,
        btn: 9,
        fun: () => addNum("9"),
      },
      {
        id: 16,
        btn: "x",
        fun: () => saveOp("x"),
      },
    ],
    [
      {
        id: 17,
        btn: 4,
        fun: () => addNum("4"),
      },
      {
        id: 18,
        btn: 5,
        fun: () => addNum("5"),
      },
      {
        id: 19,
        btn: 6,
        fun: () => addNum("6"),
      },
      {
        id: 20,
        btn: "-",
        fun: () => saveOp("-"),
      },
    ],
    [
      {
        id: 21,
        btn: 1,
        fun: () => addNum("1"),
      },
      {
        id: 22,
        btn: 2,
        fun: () => addNum("2"),
      },
      {
        id: 23,
        btn: 3,
        fun: () => addNum("3"),
      },
      {
        id: 24,
        btn: "+",
        fun: () => saveOp("+"),
      },
    ],
    [
      {
        id: 25,
        btn: "C",
        fun: () => clear(),
      },
      {
        id: 26,
        btn: 0,
        fun: () => addNum("0"),
      },
      {
        id: 27,
        btn: ",",
        fun: () => addComma(),
      },
      {
        id: 28,
        btn: "=",
        fun: () => result(),
      },
    ],
  ];

  //Variables declaradas
  const [text, setText] = useState("0");
  const [startNum, setStartNum] = useState("true");
  let [num01, setNum01] = useState(0);
  const [operator, setOp] = useState("");
  const [opPressCount, setOpPressCount] = useState(0);

  // Funcio que utilizamos para limpiar la pantalla y dejarlo como al inicio
  function clear() {
    setText("0");
    setStartNum("true");
    setNum01(0);
  }

  //Funcion que utilizamos para concatenar numeros
  function addNum(character) {
    if (startNum === "true") {
      setText(character);
      setStartNum("false");
    } else {
      setText(text + character);
    }
  }

  //Funcion quu utilizamos para guardar el operador con el que vamos a calcular
  function saveOp(op) {
    console.log(opPressCount);
    if (opPressCount === 0) {
      setOpPressCount(1);
      setNum01(checkFloat());
      setOp(op);
      setStartNum("true");
    }
  }

  //Funcion a la que llamamos cuando pulsamos la coma para añadirla y tambien ponemos un capador
  //para que no ponga mas de una coma
  function addComma() {
    if (!text.split("").includes(",")) {
      setText(text + ",");
    }
  }

  //Funcion donde accedemos para poner el resultado por pantalla de la operación seleccionada
  //a esta funcion se le llama cuando hacemos clic en el botón de =
  function result() {
    if (operator != "") {
      let result = 0;
      switch (operator) {
        case "+":
          result = num01 + checkFloat();
          setText(result.toString().replace(".", ","));
          break;
        case "-":
          result = num01 - checkFloat();
          setText(result.toString().replace(".", ","));
          break;
        case "x":
          result = num01 * checkFloat();
          setText(result.toString().replace(".", ","));
          break;
        case "/":
          result = num01 / checkFloat();
          setText(result.toString().replace(".", ","));
          break;
      }

      setOp("");
      setStartNum("true");
      setNum01(result);
    }

    setOpPressCount(0);
  }

  //Funcion que hace el calculo de radiantes
  let rad = () => checkFloat() * (Math.PI / 180);
  //Funcion que hace el calculo de grados
  let deg = () => checkFloat() / (180 / Math.PI);

  //Función donde captamos el factorial del numero que pasamos
  let fact = (n) => {
    if (n === 0) {
      return 1;
    } else {
      return n * fact(n - 1);
    }
  };

  //Antes de pasar el numero para las operaciones comprobamos si es un float para cambiar la , por el .
  let checkFloat = () => {
    if (text.split("").includes(",")) {
      return parseFloat(text.replace(",", "."));
    } else {
      return parseFloat(text);
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Calculadora</Text>

      <View style={{ marginTop: 5 }}>
        <View style={styles.screen}>
          <Text style={{ fontSize: 50, textAlign: "right", flex: 1 }}>
            {text}
          </Text>
        </View>
      </View>
      {btns.map((row, rowIndex) => (
        <View key={rowIndex} style={{ flexDirection: "row" }}>
          {row.map((item) => {
            if (typeof item.btn === "number") {
              return (
                <View key={item.id} style={{ padding: 3 }}>
                  <TouchableOpacity style={styles.btnBlue} onPress={item.fun}>
                    <Text>{item.btn}</Text>
                  </TouchableOpacity>
                </View>
              );
            } else {
              return (
                <View key={item.id} style={{ padding: 3 }}>
                  <TouchableOpacity style={styles.btnGray} onPress={item.fun}>
                    <Text>{item.btn}</Text>
                  </TouchableOpacity>
                </View>
              );
            }
          })}
        </View>
      ))}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    alignSelf: "center",
    marginVertical: 80,
  },
  title: {
    fontSize: 45,
    fontWeight: "bold",
  },
  screen: {
    flexDirection: "row",
    marginBottom: 10,
    height: 70,
    width: 340,
    borderRadius: 4,
    borderWidth: 1,
  },
  btnBlue: {
    borderRadius: 8,
    justifyContent: "center",
    alignItems: "center",
    textAlignVertical: "center",
    width: 80,
    height: 80,
    backgroundColor: "blue",
  },
  btnGray: {
    borderRadius: 8,
    justifyContent: "center",
    alignItems: "center",
    textAlignVertical: "center",
    width: 80,
    height: 80,
    backgroundColor: "gray",
  },
});
