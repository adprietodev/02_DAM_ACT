/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, {Component} from 'react';
import {
  StyleSheet,
  View,
  Text,
  Image,
  TouchableOpacity,
  ScrollView,
  Dimensions,
} from 'react-native';
const screenWidth = Dimensions.get('window').width;
const images = [
  'https://images.unsplash.com/photo-1513721032312-6a18a42c8763?w=152&h=152&fit=crop&crop=faces',
  'https://images.unsplash.com/photo-1511765224389-37f0e77cf0eb?w=125&h=125&fit=crop',
  'https://images.unsplash.com/photo-1497445462247-4330a224fdb1?w=125&h=125&fit=crop',
  'https://images.unsplash.com/photo-1426604966848-d7adac402bff?w=125&h=125&fit=crop',
  'https://images.unsplash.com/photo-1502630859934-b3b41d18206c?w=125&h=125&fit=crop',
  'https://images.unsplash.com/photo-1515023115689-589c33041d3c?w=125&h=125&fit=crop',
  'https://images.unsplash.com/photo-1504214208698-ea1916a2195a?w=125&h=125&fit=crop',
  'https://images.unsplash.com/photo-1515814472071-4d632dbc5d4a?w=125&h=125&fit=crop',
  'https://images.unsplash.com/photo-1511407397940-d57f68e81203?w=125&h=125&fit=crop',
  'https://images.unsplash.com/photo-1518481612222-68bbe828ecd1?w=125&h=125&fit=crop',
  'https://images.unsplash.com/photo-1505058707965-09a4469a87e4?w=125&h=125&fit=crop',
  'https://images.unsplash.com/photo-1423012373122-fff0a5d28cc9?w=125&h=125&fit=crop',
];

const localImg = require('./src/images/Imp_.png');

const header = () => {
  return (
    <View style={styles.head}>
      <Image style={styles.profileImg} source={localImg} />
      <View style={styles.info}>
        <View style={styles.data}>
          <View>
            <Text style={styles.text}>Posts</Text>
            <Text style={styles.text}>20</Text>
          </View>
          <View style={{alignItems: 'center'}}>
            <Text style={styles.text}>Followers</Text>
            <Text style={styles.text}>110304</Text>
          </View>
          <View>
            <Text style={styles.text}>Followings</Text>
            <Text style={styles.text}>1103</Text>
          </View>
        </View>
        <TouchableOpacity style={styles.btnEdit}>
          <Text>Edit Profile</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

export default class App extends Component {
  render() {
    return (
      <View style={styles.container}>
        <View>{header()}</View>
        <View style={styles.section2}>
          {images.map((image, index) => {
            return (
              <Image style={styles.img} key={index} source={{uri: image}} />
            );
          })}
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  head: {
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  info: {
    flex: 1,
    flexDirection: 'column',
    alignContent: 'stretch',
  },
  data: {
    flexDirection: 'row',
    justifyContent: 'space-around',
  },
  btnEdit: {
    justifyContent: 'flex-start',
    alignItems: 'center',
    backgroundColor: '#D3D3D3',
    marginHorizontal: 30,
    marginTop: 10,
    padding: 4,
  },
  text: {
    fontSize: 12,
    fontWeight: 'bold',
  },
  section2: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-around',
  },
  profileImg: {
    width: screenWidth / 4,
    height: screenWidth / 4,
    borderRadius: 50,
    marginHorizontal: 20,
    marginBottom: 20,
  },
  img: {
    width: screenWidth / 4 - 4,
    height: screenWidth / 4 - 4,
    margin: 2,
  },
});
