import { Text, View, TouchableOpacity } from 'react-native';


export default function App() {
 
return (
<View style={{ justifyContent: 'center', alignSelf: "center", marginVertical: 80 }}>

        <Text style={{ fontSize: 45, fontWeight: "bold"}}>Calculadora</Text>

        <View style={{ marginTop: 5}}>
          <View style={{ flexDirection: "row", marginBottom: 10, height: 70, width: 340, borderRadius: 4, borderWidth: 1 }}>
            <Text style={{ fontSize: 50, textAlign: "right", flex: 1 }}>Hola</Text>
          </View>
          <View style={{ flexDirection: "row" }}>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>sen</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>cos</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>tan</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>deg</Text></TouchableOpacity>
            </View>
          </View>
            <View style={{ flexDirection: "row" }}>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>ln</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>log</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>&Pi;</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>rad</Text></TouchableOpacity>
            </View>
          </View>
          <View style={{ flexDirection: "row" }}>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>1/X</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>!</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>√</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>/</Text></TouchableOpacity>
            </View>
          </View>
          <View style={{ flexDirection: "row" }}>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'blue'}}><Text>7</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'blue'}}><Text>8</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'blue'}}><Text>9</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>x</Text></TouchableOpacity>
            </View>
          </View>
          <View style={{ flexDirection: "row" }}>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'blue'}}><Text>4</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'blue'}}><Text>5</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'blue'}}><Text>6</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>-</Text></TouchableOpacity>
            </View>
          </View>
          <View style={{ flexDirection: "row" }}>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'blue'}}><Text>1</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'blue'}}><Text>2</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'blue'}}><Text>3</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>+</Text></TouchableOpacity>
            </View>
          </View>
          <View style={{ flexDirection: "row" }}>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>C</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'blue'}}><Text>0</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>,</Text></TouchableOpacity>
            </View>
            <View style={{ padding: 3 }}>
              <TouchableOpacity style={{borderRadius: 8, justifyContent: 'center', alignItems: 'center', textAlignVertical: 'center', width: 80, height: 80, backgroundColor: 'gray'}}><Text>=</Text></TouchableOpacity>
            </View>
          </View>
        </View>

  </View>
)
}

