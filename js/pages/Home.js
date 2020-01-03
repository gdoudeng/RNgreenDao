import React, {Component} from 'react';
import {Text, View, StyleSheet, FlatList, Image, TextInput, ScrollView} from 'react-native';
import {Button, Card, Colors} from 'react-native-paper';

class Home extends Component {
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
    };
  }

  static navigationOptions = {
    title: '数据库操作',
  };

  _insert = () => {
  };

  _queryAllUser = () => {
  };

  _queryUserByName = () => {
  };

  _queryUserAgeBigThan18DescOrderByAge = () => {
  };

  queryUserSize = () => {
  };

  _updateUser1 = () => {
  };

  _updateUser2 = () => {
  };

  _deleteById = () => {
  };

  _deleteByName = () => {
  };

  _deleteAllUser = () => {
  };

  _deleteUserAgeLowThan18 = () => {
  };

  render() {
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
    } = this.state;
    return (
        <View style={styles.container}>
          <ScrollView contentContainerStyle={styles.contentContainer}>
            <Card style={styles.cardViewWrap}>
              <Card.Title title="查询结果展示" subtitle={title}/>
              <Card.Content>
                {list.map(valueObj => {
                  return <View/>;
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
                        onPress={this._queryUserByName}>{actionName3}</Button>
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
                        onPress={this._deleteAllUser}>{actionName10}</Button>
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._deleteByName}>{actionName11}</Button>
              </Card.Content>
            </Card>
          </ScrollView>
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
});
