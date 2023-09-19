import { StatusBar } from "expo-status-bar";
import { ScrollView, StyleSheet, Text, View } from "react-native";

export default function App() {
  let title = "First Steps in React Nactive";
  let content = {
    paragraphOne: "Primer cadena de texto",
    paragraphTwo: "Segunda cadena de texto",
    title2: "Esta es la primera app",
  };

  function Article() {
    return (
      <View style={styles.container}>
        <Text style={styles.title}>{content.title2}</Text>
        <Text>{content.paragraphOne}</Text>
        <Text>{content.paragraphTwo}</Text>
      </View>
    );
  }

  function Articles() {
    let article = [];
    for (let i = 0; i < 4; i++) {
      article.push(<Article key={i.toString()}></Article>);
    }
    return article;
  }

  return (
    <>
      <Article></Article>
      <Article></Article>
      <Articles></Articles>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#b0e0e6",
    alignItems: "center",
    justifyContent: "center",
  },
  title: {
    fontSize: 12,
    fontWeight: "bold",
    textDecorationLine: "underline",
  },
  footer: {
    flex: 1,
    backgroundColor: "blue",
    maxHeight: 15,
  },
});
