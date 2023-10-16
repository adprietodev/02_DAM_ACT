import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";

export default function App() {
  let squaresG = [
    <View width={150} height={150} backgroundColor={"blue"} />,
    <View width={150} height={150} backgroundColor={"red"} />,
  ];
  let squaresP = [
    <View width={100} height={100} backgroundColor={"blue"} />,
    <View width={100} height={100} backgroundColor={"red"} />,
  ];
  let triangle = [
    <View
      width={0}
      height={0}
      backgroundColor={"transparent"}
      borderStyle={"solid"}
      borderTopWidth={0}
      borderRightWidth={45}
      borderBottomWidth={90}
      borderLeftWidth={45}
      borderTopColor={"transparent"}
      borderRightColor={"transparent"}
      borderBottomColor={"blue"}
      borderLeftColor={"transparent"}
    />,
    <View
      width={0}
      height={0}
      backgroundColor={"transparent"}
      borderStyle={"solid"}
      borderTopWidth={0}
      borderRightWidth={45}
      borderBottomWidth={90}
      borderLeftWidth={45}
      borderTopColor={"transparent"}
      borderRightColor={"transparent"}
      borderBottomColor={"red"}
      borderLeftColor={"transparent"}
    />,
  ];
  let circle = [
    <View
      width={100}
      height={100}
      borderRadius={100 / 2}
      backgroundColor={"blue"}
    />,
    <View
      width={100}
      height={100}
      borderRadius={100 / 2}
      backgroundColor={"red"}
    />,
    <View
      width={100}
      height={100}
      borderRadius={100 / 2}
      backgroundColor={"green"}
    />,
  ];

  let circleD4G = [
    <View
      width={100}
      height={100}
      borderTopLeftRadius={150}
      borderTopRightRadius={0}
      borderBottomLeftRadius={0}
      borderBottomRightRadius={0}
      borderColor={"blue"}
      borderWidth={1}
      backgroundColor={"blue"}
    />,
    <View
      width={100}
      height={100}
      borderTopLeftRadius={0}
      borderTopRightRadius={150}
      borderBottomLeftRadius={0}
      borderBottomRightRadius={0}
      borderColor={"red"}
      borderWidth={1}
      backgroundColor={"red"}
    />,
    <View
      width={100}
      height={100}
      borderTopLeftRadius={0}
      borderTopRightRadius={0}
      borderBottomLeftRadius={150}
      borderBottomRightRadius={0}
      borderColor={"red"}
      borderWidth={1}
      backgroundColor={"red"}
    />,
    <View
      width={100}
      height={100}
      borderTopLeftRadius={0}
      borderTopRightRadius={0}
      borderBottomLeftRadius={0}
      borderBottomRightRadius={150}
      borderColor={"blue"}
      borderWidth={1}
      backgroundColor={"blue"}
    />,
  ];

  let circleD4M = [
    <View
      width={50}
      height={50}
      borderTopLeftRadius={150}
      borderTopRightRadius={0}
      borderBottomLeftRadius={0}
      borderBottomRightRadius={0}
      borderColor={"blue"}
      borderWidth={1}
      backgroundColor={"blue"}
    />,
    <View
      width={50}
      height={50}
      borderTopLeftRadius={0}
      borderTopRightRadius={150}
      borderBottomLeftRadius={0}
      borderBottomRightRadius={0}
      borderColor={"red"}
      borderWidth={1}
      backgroundColor={"red"}
    />,
    <View
      width={50}
      height={50}
      borderTopLeftRadius={0}
      borderTopRightRadius={0}
      borderBottomLeftRadius={150}
      borderBottomRightRadius={0}
      borderColor={"red"}
      borderWidth={1}
      backgroundColor={"red"}
    />,
    <View
      width={50}
      height={50}
      borderTopLeftRadius={0}
      borderTopRightRadius={0}
      borderBottomLeftRadius={0}
      borderBottomRightRadius={150}
      borderColor={"blue"}
      borderWidth={1}
      backgroundColor={"blue"}
    />,
  ];

  let circleD4P = [
    <View
      width={25}
      height={25}
      borderTopLeftRadius={150}
      borderTopRightRadius={0}
      borderBottomLeftRadius={0}
      borderBottomRightRadius={0}
      borderColor={"blue"}
      borderWidth={1}
      backgroundColor={"blue"}
    />,
    <View
      width={25}
      height={25}
      borderTopLeftRadius={0}
      borderTopRightRadius={150}
      borderBottomLeftRadius={0}
      borderBottomRightRadius={0}
      bborderColor={"red"}
      borderWidth={1}
      backgroundColor={"red"}
    />,
    <View
      width={25}
      height={25}
      borderTopLeftRadius={0}
      borderTopRightRadius={0}
      borderBottomLeftRadius={150}
      borderBottomRightRadius={0}
      borderColor={"red"}
      borderWidth={1}
      backgroundColor={"red"}
    />,
    <View
      width={25}
      height={25}
      borderTopLeftRadius={0}
      borderTopRightRadius={0}
      borderBottomLeftRadius={0}
      borderBottomRightRadius={150}
      borderColor={"blue"}
      borderWidth={1}
      backgroundColor={"blue"}
    />,
  ];

  let d01 = [
    [squaresG[0]],
    [squaresG[1], squaresG[1]],
    [squaresG[1], squaresG[1]],
  ];

  let d02 = [
    [squaresG[0], squaresG[0]],
    [squaresG[1], squaresG[0]],
    [squaresG[1], squaresG[1]],
  ];

  let d03 = [[triangle[0]], [squaresP[0]], [squaresP[0], squaresP[0]]];

  let d04 = [
    [circle[1], circle[0], circle[2]],
    [circle[0], circle[2], circle[1]],
    [circle[2], circle[1], circle[0]],
  ];

  let d05 = [
    [circleD4G[0], circleD4G[1]],
    [circleD4G[2], circleD4G[3]],
    [circleD4M[0], circleD4M[1]],
    [circleD4M[2], circleD4M[3]],
    [circleD4P[0], circleD4P[1]],
    [circleD4P[2], circleD4P[3]],
  ];

  return (
    <View style={styles.container}>
      {d05.map((row) => (
        <View style={{ flexDirection: "row" }}>
          {row.map((element) => element)}
        </View>
      ))}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
