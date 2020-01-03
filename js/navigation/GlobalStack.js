import React from 'react';
import {StatusBar} from 'react-native';
import {createAppContainer} from 'react-navigation';
import {createStackNavigator, TransitionPresets} from 'react-navigation-stack';
import LoginScreen from '../pages/Login';
import HomeScreen from '../pages/Home';

export default function (initialRouteName) {
  return createAppContainer(createStackNavigator({
        Login: {
          screen: LoginScreen,
        },
        Home: {
          screen: HomeScreen,
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
