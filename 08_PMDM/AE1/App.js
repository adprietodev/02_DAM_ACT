import { useState } from "react";
import { Text, View, TouchableOpacity, StyleSheet } from "react-native";

export default function App() {
  const [text, setText] = useState("0");
  const [startNum, setStartNum] = useState("true");
  let [num01, setNum01] = useState(0);
  const [operator, setOp] = useState("");

  const props = [
    ["sen", "cos", "tan", "deg"],
    ["ln", "log", "\u03C0", "rad"],
    ["1/X", "!", "√", "/"],
    [7, 8, 9, "x"],
    [4, 5, 6, "-"],
    [1, 2, 3, "+"],
    ["C", 0, ",", "="],
  ];

  function checkBtnPress(c) {
    if (c === "sen") {
      setText(Math.sin(rad()).toString().replace(".", ","));
      setStartNum("true");
    }
    if (c === "cos") {
      setText(Math.cos(rad()).toString().replace(".", ","));
      setStartNum("true");
    }
    if (c === "tan") {
      setText(Math.tan(rad()).toString().replace(".", ","));
      setStartNum("true");
    }
    if (c === "deg") {
      setText(deg().toString().replace(".", ","));
      setStartNum("true");
    }
    if (c === "ln") {
      setText(Math.log(checkFloat()).toString().replace(".", ","));
      setStartNum("true");
    }
    if (c === "log") {
      setText(Math.log10(checkFloat()).toString().replace(".", ","));
      setStartNum("true");
    }
    if (c === "\u03C0") {
      setText(Math.PI.toString().replace(".", ","));
      setStartNum("true");
    }
    if (c === "rad") {
      setText(rad().toString().replace(".", ","));
      setStartNum("true");
    }
    if (c === "1/X") {
      setText((1 / checkFloat()).toString().replace(".", ","));
      setStartNum("true");
    }
    if (c === "!") {
      setText(fact(parseInt(checkFloat())).toString().replace(".", ","));
      setStartNum("true");
    }
    if (c === "√") {
      setText(Math.sqrt(checkFloat()).toString().replace(".", ","));
      setStartNum("true");
    }
    if (c === "/" || c === "x" || c === "-" || c === "+") {
      saveOp(c);
    }
    if (!Number.isNaN(c)) {
      for (let i = 0; i <= 9; i++) {
        if (c === i) {
          addNum(c.toString());
        }
      }
    }
    if (c === "C") clear();
    if (c === ",") addComma();
    if (c === "=") result();
  }

  function clear() {
    setText("0");
    setStartNum("true");
    setNum01(0);
  }
  function addNum(character) {
    if (startNum === "true") {
      setText(character);
      setStartNum("false");
    } else {
      setText(text + character);
    }
  }
  function saveOp(op) {
    setNum01(checkFloat());
    setOp(op);
    setStartNum("true");
  }
  function addComma() {
    if (!text.split("").includes(",") && text != "0") {
      setText(text + ",");
    }
  }

  function result() {
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
    setNum01(parseFloat(text));
    setOp("");
    setStartNum("true");
  }
  let rad = () => checkFloat() * (Math.PI / 180);
  let deg = () => checkFloat() / (180 / Math.PI);
  let fact = (n) => {
    if (n === 0) {
      return 1;
    } else {
      return n * fact(n - 1);
    }
  };
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
      {props.map((row, index) => (
        <View key={index} style={{ flexDirection: "row" }}>
          {row.map((e, index) => {
            if (typeof e === "number") {
              return (
                <View key={index} style={{ padding: 3 }}>
                  <TouchableOpacity
                    style={styles.btnBlue}
                    onPress={() => checkBtnPress(e)}
                  >
                    <Text>{e}</Text>
                  </TouchableOpacity>
                </View>
              );
            } else {
              return (
                <View key={index} style={{ padding: 3 }}>
                  <TouchableOpacity
                    style={styles.btnGray}
                    onPress={() => checkBtnPress(e)}
                  >
                    <Text>{e}</Text>
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
