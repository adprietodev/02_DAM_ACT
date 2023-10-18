import { useState } from "react";
import { Text, View, TouchableOpacity, StyleSheet } from "react-native";

export default function App() {
  const [text, setText] = useState("0");
  const [startNum, setStartNum] = useState("true");
  let [num01, setNum01] = useState(0);
  //let [num02, setNum02] = useState(0);
  const [operator, setOp] = useState("");

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
    <View
      style={{
        justifyContent: "center",
        alignSelf: "center",
        marginVertical: 80,
      }}
    >
      <Text style={{ fontSize: 45, fontWeight: "bold" }}>Calculadora</Text>

      <View style={{ marginTop: 5 }}>
        <View
          style={{
            flexDirection: "row",
            marginBottom: 10,
            height: 70,
            width: 340,
            borderRadius: 4,
            borderWidth: 1,
          }}
        >
          <Text style={{ fontSize: 50, textAlign: "right", flex: 1 }}>
            {text}
          </Text>
        </View>
        <View style={{ flexDirection: "row" }}>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => {
                setText(Math.sin(rad()).toString().replace(".", ","));
                setStartNum("true");
              }}
            >
              <Text>sen</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => {
                setText(Math.cos(rad()).toString().replace(".", ","));
                setStartNum("true");
              }}
            >
              <Text>cos</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => {
                setText(Math.tan(rad()).toString().replace(".", ","));
                setStartNum("true");
              }}
            >
              <Text>tan</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => {
                setText(deg().toString().replace(".", ","));
                setStartNum("true");
              }}
            >
              <Text>deg</Text>
            </TouchableOpacity>
          </View>
        </View>
        <View style={{ flexDirection: "row" }}>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => {
                setText(Math.log(checkFloat()).toString().replace(".", ","));
                setStartNum("true");
              }}
            >
              <Text>ln</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => {
                setText(Math.log10(checkFloat()).toString().replace(".", ","));
                setStartNum("true");
              }}
            >
              <Text>log</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => {
                setText(Math.PI.toString().replace(".", ","));
                setStartNum("true");
              }}
            >
              <Text>&Pi;</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => {
                setText(rad().toString().replace(".", ","));
                setStartNum("true");
              }}
            >
              <Text>rad</Text>
            </TouchableOpacity>
          </View>
        </View>
        <View style={{ flexDirection: "row" }}>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => {
                setText((1 / checkFloat()).toString().replace(".", ","));
                setStartNum("true");
              }}
            >
              <Text>1/X</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => {
                setText(fact(parseInt(checkFloat())));
                setStartNum("true");
              }}
            >
              <Text>!</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => {
                setText(Math.sqrt(checkFloat()));
                setStartNum("true");
              }}
            >
              <Text>√</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => saveOp("/")}
            >
              <Text>/</Text>
            </TouchableOpacity>
          </View>
        </View>
        <View style={{ flexDirection: "row" }}>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "blue",
              }}
              onPress={() => addNum("7")}
            >
              <Text>7</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "blue",
              }}
              onPress={() => addNum("8")}
            >
              <Text>8</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "blue",
              }}
              onPress={() => addNum("9")}
            >
              <Text>9</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => saveOp("x")}
            >
              <Text>x</Text>
            </TouchableOpacity>
          </View>
        </View>
        <View style={{ flexDirection: "row" }}>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "blue",
              }}
              onPress={() => addNum("4")}
            >
              <Text>4</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "blue",
              }}
              onPress={() => addNum("5")}
            >
              <Text>5</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "blue",
              }}
              onPress={() => addNum("6")}
            >
              <Text>6</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => saveOp("-")}
            >
              <Text>-</Text>
            </TouchableOpacity>
          </View>
        </View>
        <View style={{ flexDirection: "row" }}>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "blue",
              }}
              onPress={() => addNum("1")}
            >
              <Text>1</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "blue",
              }}
              onPress={() => addNum("2")}
            >
              <Text>2</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "blue",
              }}
              onPress={() => addNum("3")}
            >
              <Text>3</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={() => saveOp("+")}
            >
              <Text>+</Text>
            </TouchableOpacity>
          </View>
        </View>
        <View style={{ flexDirection: "row" }}>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={clear}
            >
              <Text>C</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "blue",
              }}
              onPress={() => addNum("0")}
            >
              <Text>0</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={addComma}
            >
              <Text>,</Text>
            </TouchableOpacity>
          </View>
          <View style={{ padding: 3 }}>
            <TouchableOpacity
              style={{
                borderRadius: 8,
                justifyContent: "center",
                alignItems: "center",
                textAlignVertical: "center",
                width: 80,
                height: 80,
                backgroundColor: "gray",
              }}
              onPress={result}
            >
              <Text>=</Text>
            </TouchableOpacity>
          </View>
        </View>
      </View>
    </View>
  );
}
