import React from 'react';
import {ScrollView, StyleSheet, Text, TextInput, ToastAndroid, View} from 'react-native';
import {Button, Card, Colors, Dialog, Portal} from 'react-native-paper';
import UserDao from '../dao/UserDao';
import BaseComponent from '../components/BaseComponent';

class Home extends BaseComponent {
  constructor(props) {
    super(props);
    this.state = {
      title: '查询所有用户',
      actionName1: '查询全部用户',
      actionName2: '根据用户名查询数据',
      actionName3: '查询姓名为张三的用户',
      actionName4: '查询年龄大于等于18的用户，并按年龄降序排列',
      actionName5: '查询用户数量',
      actionName6: '将所有年龄大于等于18岁的用户改名为成年人',
      actionName7: '将id为2的用户名改为渣渣辉',
      actionName8: '删除id为1的用户',
      actionName9: '删除年龄小于18的用户',
      actionName10: '删除全部用户数据',
      actionName11: '根据用户名删除数据',
      list: [],
      username: '',
      userkey: '',
      age: '',
      deleteUserName: '',
      searchUserName: '',
      visible: false,
    };
  }

  _checkUserKey = userKey => {
    return /^[0-9]*$/.test(userKey);
  };

  _checkUserAge = age => {
    return /^[0-9]*$/.test(age);
  };

  _insert = () => {
    const {userkey, username, age} = this.state;
    if (this._checkUserKey(userkey) && username && this._checkUserAge(age)) {
      UserDao.addUser(userkey, username, age, success => {
        ToastAndroid.show('插入成功', ToastAndroid.SHORT);
        this.setState({userkey: '', username: '', age: ''});
      });
    } else {
      ToastAndroid.show('信息不正确', ToastAndroid.SHORT);
    }
  };

  _queryAllUser = () => {
    const {actionName1} = this.state;
    UserDao.getAllUser().then(result => {
      this.setState({title: [actionName1], list: result});
      ToastAndroid.show('查询成功', ToastAndroid.SHORT);
    }).catch(e => {
      ToastAndroid.show(e, ToastAndroid.SHORT);
    });
  };

  _queryUserByName = () => {
    const {searchUserName, actionName2} = this.state;
    if (searchUserName) {
      UserDao.queryUserByUserName(searchUserName, result => {
        if (result.length > 0) {
          this.setState({title: [actionName2], list: result});
          ToastAndroid.show('查询成功', ToastAndroid.SHORT);
        } else {
          ToastAndroid.show('没有查询到数据', ToastAndroid.SHORT);
        }
      });
    } else {
      ToastAndroid.show('请输入要查询的用户名', ToastAndroid.SHORT);
    }

  };

  _queryZSUser = () => {
    const {actionName3} = this.state;
    UserDao.queryUserByUserName('张三', result => {
      this.setState({title: [actionName3], list: result});
      ToastAndroid.show('查询成功', ToastAndroid.SHORT);
    });
  };
  _queryUserAgeBigThan18DescOrderByAge = () => {
    const {actionName4} = this.state;
    UserDao.queryUserOlderThan18OrderByAge(result => {
      this.setState({title: [actionName4], list: result});
      ToastAndroid.show('查询成功', ToastAndroid.SHORT);
    });

  };

  queryUserSize = () => {
    UserDao.getUserSize(result => {
      if (result) {
        ToastAndroid.show('查询成功，用户数量为 ' + result.size, ToastAndroid.SHORT);
      }
    });
  };

  _updateUser1 = () => {
  };

  _updateUser2 = () => {
  };

  _deleteById = () => {
    UserDao.deleteUserById(1, success => {
      ToastAndroid.show('删除成功', ToastAndroid.SHORT);
    });
  };

  _deleteByName = () => {
    const {deleteUserName} = this.state;
    if (deleteUserName) {
      UserDao.deleteUserByUserName(deleteUserName, success => {
        this.setState({deleteUserName: ''});
        ToastAndroid.show('删除成功', ToastAndroid.SHORT);
      });
    } else {
      ToastAndroid.show('请填写要删除的用户名', ToastAndroid.SHORT);
    }
  };


  _deleteAllUser = () => {
    UserDao.deleteAllUser(success => {
      ToastAndroid.show('已删除所有用户', ToastAndroid.SHORT);
    });
  };

  _deleteUserAgeLowThan18 = () => {
    UserDao.deleteUserWhoUnder18(success => {
      ToastAndroid.show('删除成功', ToastAndroid.SHORT);
    });
  };

  _showDialog = () => this.setState({visible: true});

  _hideDialog = () => this.setState({visible: false});

  _confirmDeleteAllUser = () => {
    this._hideDialog();
    this._deleteAllUser();
  };

  _render() {
    const {
      list,
      title,
      userkey,
      username,
      age,
      actionName1,
      actionName2,
      actionName3,
      actionName4,
      actionName5,
      actionName6,
      actionName7,
      actionName8,
      actionName9,
      actionName10,
      actionName11,
      deleteUserName,
      searchUserName,
      visible,
    } = this.state;
    return (
        <View style={styles.container}>
          <ScrollView contentContainerStyle={styles.contentContainer}>
            <Card style={styles.cardViewWrap}>
              <Card.Title title="查询结果展示" subtitle={title}/>
              <Card.Content>
                {list.map(valueObj => {
                  return <View style={styles.itemViewWrap} key={valueObj.id}>
                    <Text style={styles.itemText}>id：{valueObj.id}</Text>
                    <Text style={styles.itemText}>姓名：{valueObj.username}</Text>
                    <Text style={styles.itemText}>年龄：{valueObj.age}</Text>
                  </View>;
                })}
              </Card.Content>
            </Card>

            <Card style={styles.cardViewWrap}>
              <Card.Title title="增"/>
              <Card.Content style={styles.searchContentView}>
                <TextInput
                    style={styles.textField}
                    placeholder={'输入用户id（主键）'}
                    returnKeyType={'next'}
                    returnKeyLabel={'next'}
                    keyboardType={'number-pad'}
                    maxLength={20}
                    onSubmitEditing={() => {
                      this.usernameTextField.focus();
                    }}
                    onChangeText={(text) => {
                      this.setState({userkey: text});
                    }}
                    value={userkey}
                />
                <TextInput
                    ref={(ref) => this.usernameTextField = ref}
                    style={styles.textField}
                    placeholder={'输入用户姓名'}
                    returnKeyType={'next'}
                    returnKeyLabel={'next'}
                    keyboardType={'default'}
                    maxLength={20}
                    onSubmitEditing={() => {
                      this.ageTextField.focus();
                    }}
                    onChangeText={(text) => {
                      this.setState({username: text});
                    }}
                    value={username}
                />
                <TextInput
                    ref={(ref) => this.ageTextField = ref}
                    style={styles.textField}
                    placeholder={'输入用户年龄'}
                    returnKeyType={'done'}
                    returnKeyLabel={'done'}
                    keyboardType={'number-pad'}
                    maxLength={20}
                    onChangeText={(text) => {
                      this.setState({age: text});
                    }}
                    onSubmitEditing={this._insert}
                    value={age}
                />
              </Card.Content>
              <Card.Actions>
                <Button mode="outlined" color={Colors.black} onPress={this._insert}>插入数据</Button>
              </Card.Actions>
            </Card>

            <Card style={styles.cardViewWrap}>
              <Card.Title title="查"/>
              <Card.Content style={styles.searchContentView}>
                <TextInput
                    style={styles.textField}
                    placeholder={'输入要查询的用户名'}
                    returnKeyType={'next'}
                    returnKeyLabel={'next'}
                    keyboardType={'default'}
                    maxLength={20}
                    onSubmitEditing={this._queryUserByName}
                    onChangeText={(text) => {
                      this.setState({searchUserName: text});
                    }}
                    value={searchUserName}
                />
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._queryAllUser}>{actionName1}</Button>
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._queryUserByName}>{actionName2}</Button>
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._queryZSUser}>{actionName3}</Button>
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._queryUserAgeBigThan18DescOrderByAge}>{actionName4}</Button>
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this.queryUserSize}>{actionName5}</Button>
              </Card.Content>
            </Card>

            <Card style={styles.cardViewWrap}>
              <Card.Title title="改"/>
              <Card.Content style={styles.searchContentView}>
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._updateUser1}>{actionName6}</Button>
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._updateUser2}>{actionName7}</Button>
              </Card.Content>
            </Card>

            <Card style={styles.cardViewWrap}>
              <Card.Title title="删"/>
              <Card.Content style={styles.searchContentView}>
                <TextInput
                    style={styles.textField}
                    placeholder={'输入要删除的用户名'}
                    returnKeyType={'next'}
                    returnKeyLabel={'next'}
                    keyboardType={'default'}
                    maxLength={20}
                    onSubmitEditing={this._deleteByName}
                    onChangeText={(text) => {
                      this.setState({deleteUserName: text});
                    }}
                    value={deleteUserName}
                />
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._deleteById}>{actionName8}</Button>
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._deleteUserAgeLowThan18}>{actionName9}</Button>
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._showDialog}>{actionName10}</Button>
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._deleteByName}>{actionName11}</Button>
              </Card.Content>
            </Card>
          </ScrollView>

          <Portal>
            <Dialog
                visible={visible}
                onDismiss={this._hideDialog}>
              <Dialog.Title>提示</Dialog.Title>
              <Dialog.Content>
                <Text style={styles.logoutContent}>确认删除所有用户数据?</Text>
              </Dialog.Content>
              <Dialog.Actions>
                <Button onPress={this._hideDialog} style={styles.logoutBtn} color="#000000">取消</Button>
                <Button onPress={this._confirmDeleteAllUser} style={styles.logoutBtn} color="#000000">确定</Button>
              </Dialog.Actions>
            </Dialog>
          </Portal>
        </View>
    );
  }
}

export default Home;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: Colors.white,
  },
  contentContainer: {
    paddingBottom: 30,
  },
  itemViewWrap: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    alignItems: 'center',
  },
  itemText: {
    fontSize: 14,
  },
  noListView: {
    justifyContent: 'center',
    alignItems: 'center',
  },
  bottomfoot: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 10,
  },
  footText: {
    fontSize: 15,
    color: '#999999',
  },
  noListImage: {
    height: 300,
    width: 300,
  },
  cardViewWrap: {
    marginHorizontal: 13,
    marginTop: 10,
  },
  listView: {
    height: 300,
  },
  textField: {
    borderBottomWidth: StyleSheet.hairlineWidth,
    borderColor: '#dcdcdc',
    marginRight: 10,
    paddingLeft: 10,
    paddingRight: 10,
  },
  searchContentView: {
    flexDirection: 'row',
    alignItems: 'center',
    flexWrap: 'wrap',
  },
  buttonStyle: {
    marginTop: 5,
    marginRight: 5,
  },
  logoutBtn: {
    marginRight: 8,
  },
  logoutContent: {
    fontSize: 16,
  },
});
