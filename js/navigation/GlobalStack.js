import React from 'react';
import {TouchableOpacity, DeviceEventEmitter, StyleSheet} from 'react-native';
import {createAppContainer} from 'react-navigation';
import {createStackNavigator, TransitionPresets} from 'react-navigation-stack';
import LoginScreen from '../pages/Login';
import HomeScreen from '../pages/Home';
import Home2Screen from '../pages/Home2';
import AntDesign from 'react-native-vector-icons/AntDesign';

export default function (initialRouteName) {
  return createAppContainer(createStackNavigator({
        Login: {
          screen: LoginScreen,
        },
        Home: {
          screen: HomeScreen,
          navigationOptions: {
            title: '单表操作',
            headerRightContainerStyle: styles.headerRightContainerStyle,
            headerRight: () => headerRight,
          },
        },
        Home2: {
          screen: Home2Screen,
          navigationOptions: {
            title: '多表操作',
            headerRightContainerStyle: styles.headerRightContainerStyle,
            headerRight: () => headerRight,
          },
        },
      },
      {
        initialRouteName,
        defaultNavigationOptions: {
          gestureEnabled: true,
          safeAreaInsets: {top: 0},
          ...TransitionPresets.SlideFromRightIOS,
        },
      }));
}

const styles = StyleSheet.create({
  headerRightContainerStyle: {paddingRight: 16},
  icon: {paddingHorizontal: 7},
});

const headerRight = <TouchableOpacity
    onPress={() => {
      DeviceEventEmitter.emit('globalStack/logout');
    }}
>
  <AntDesign name="logout"
             size={25}
             style={styles.icon}/>
</TouchableOpacity>;
