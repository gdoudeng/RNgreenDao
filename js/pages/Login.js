import React, {Component} from 'react';
import {StyleSheet, TouchableNativeFeedback, View} from 'react-native';
import {Button, Colors, RadioButton, Subheading} from 'react-native-paper';

class Login extends Component {
  static navigationOptions = {
    title: 'greenDAO的使用',
  };

  constructor(props) {
    super(props);
    this.state = {
      checked: 'Home',
    };
  }

  login = () => {
    const {checked} = this.state;
    this.props.navigation.replace(checked);
  };

  render() {
    const {checked} = this.state;

    return (
        <View style={styles.container}>
          <TouchableNativeFeedback
              onPress={() => this.setState({checked: 'Home'})}
          >
            <View style={styles.row}>
              <View pointerEvents="none">
                <RadioButton
                    value="Home"
                    status={checked === 'Home' ? 'checked' : 'unchecked'}
                    color='#0178f6'
                />
              </View>
              <Subheading style={styles.text}>单表使用</Subheading>
            </View>
          </TouchableNativeFeedback>
          <TouchableNativeFeedback
              onPress={() => this.setState({checked: 'Home2'})}
          >
            <View style={styles.row}>
              <View pointerEvents="none">
                <RadioButton
                    value="Home2"
                    status={checked === 'Home2' ? 'checked' : 'unchecked'}
                    color='#0178f6'
                />
              </View>
              <Subheading style={styles.text}>多表关联</Subheading>
            </View>
          </TouchableNativeFeedback>
          <Button mode="outlined" color={Colors.black} onPress={this.login} style={styles.btn}>进入</Button>
        </View>
    );
  }
}

export default Login;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 10,
    paddingHorizontal: 10,
  },
  row: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 16,
    paddingVertical: 8,
  },
  text: {
    paddingLeft: 8,
  },
  btn: {
    marginTop: 10,
  },
});
