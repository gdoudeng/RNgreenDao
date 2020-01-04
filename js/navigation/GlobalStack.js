import React from 'react';
import {createAppContainer} from 'react-navigation';
import {createStackNavigator, TransitionPresets} from 'react-navigation-stack';
import LoginScreen from '../pages/Login';
import HomeScreen from '../pages/Home';
import Home2Screen from '../pages/Home2';

export default function (initialRouteName) {
  return createAppContainer(createStackNavigator({
        Login: {
          screen: LoginScreen,
        },
        Home: {
          screen: HomeScreen,
        },
        Home2: {
          screen: Home2Screen,
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
