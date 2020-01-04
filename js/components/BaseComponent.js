import React, {Component} from 'react';
import {StyleSheet, View, Text, DeviceEventEmitter} from 'react-native';
import {NavigationActions, StackActions} from 'react-navigation';
import {Button, Dialog, Portal} from 'react-native-paper';

class BaseComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      logoutDialogVisible: false,
    };
    this._render = this._render.bind(this);
  }

  componentDidMount() {
    this.logoutEventListener = DeviceEventEmitter.addListener('globalStack/logout', this._showLogoutDialog);
    this._componentDidMount();
  }

  _componentDidMount() {
  }

  componentWillUnmount() {
    this.logoutEventListener && this.logoutEventListener.remove();
    this._componentWillUnmount();
  }

  _componentWillUnmount() {
  }

  _showLogoutDialog = () => this.setState({logoutDialogVisible: true});

  _hideLogoutDialog = () => this.setState({logoutDialogVisible: false});

  _confirmLogout = () => {
    this._hideLogoutDialog();
    // 重置路由
    const resetAction = StackActions.reset({
      index: 0,
      actions: [
        NavigationActions.navigate({
          routeName: 'Login',
        }),
      ],
    });
    this.props.navigation.dispatch(resetAction);
  };

  renderLogoutDialog() {
    // 这里能做的东西太多了 可以全局强制下线 可以全局提示登陆过期 可以全局登出
    const {logoutDialogVisible} = this.state;
    return (
        <Portal>
          <Dialog
              visible={logoutDialogVisible}
              onDismiss={this._hideLogoutDialog}>
            <Dialog.Title>提示</Dialog.Title>
            <Dialog.Content>
              <Text style={styles.logoutContent}>确认退出登录?</Text>
            </Dialog.Content>
            <Dialog.Actions>
              <Button onPress={this._hideLogoutDialog} style={styles.logoutBtn} color="#000000">取消</Button>
              <Button onPress={this._confirmLogout} style={styles.logoutBtn} color="#000000">确定</Button>
            </Dialog.Actions>
          </Dialog>
        </Portal>
    );
  }

  _render() {
    return null;
  }

  render() {
    return (
        <View style={[styles.container, this.props.style]}>
          {this._render()}
          {this.renderLogoutDialog()}
        </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#ffffff',
  },
  logoutBtn: {
    marginRight: 8,
  },
  logoutContent: {
    fontSize: 16,
  },
});

export default BaseComponent;
