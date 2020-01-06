import React from 'react';
import {ScrollView, StyleSheet, Text, TextInput, ToastAndroid, View} from 'react-native';
import BaseComponent from '../components/BaseComponent';
import {Button, Card, Colors} from 'react-native-paper';
import StudentDao from '../dao/StudentDao';
import IdCardDao from '../dao/IdCardDao';

class Home2 extends BaseComponent {
  constructor(props) {
    super(props);
    this.state = {
      title: '查询所有',
      idCardTitle: '查询所有',
      actionName1: '查询全部学生',
      actionName3: '查询所有IdCard',
      actionName2: '根据学生ID查询IDCard',
      actionName4: '根据学号查询IDCard',
      studentList: [],
      idCardList: [],
      studentKey: '',
      studentNo: '',
    };
  }

  _getAllStudent = () => {
    const {actionName1} = this.state;
    StudentDao.getAllStudent().then(result => {
      this.setState({title: [actionName1], studentList: result});
      ToastAndroid.show('查询成功', ToastAndroid.SHORT);
    }).catch(e => {
      ToastAndroid.show(e, ToastAndroid.SHORT);
    });
  };

  _getAllIdCard = () => {
    const {actionName3} = this.state;
    IdCardDao.getAllIdCard().then(result => {
      this.setState({idCardTitle: [actionName3], idCardList: result});
      ToastAndroid.show('查询成功', ToastAndroid.SHORT);
    }).catch(e => {
      ToastAndroid.show(e, ToastAndroid.SHORT);
    });
  };

  _getIdCardByStudentId = () => {
    const {studentKey, actionName2} = this.state;
    if (studentKey) {
      StudentDao.getIdCardByStudentId(studentKey, result => {
        this.setState({idCardTitle: [actionName2], studentKey: '', idCardList: [result]});
        ToastAndroid.show('查询成功', ToastAndroid.SHORT);
      });
    } else {
      ToastAndroid.show('请输入要查询的学生id', ToastAndroid.SHORT);
    }
  };

  _getIdCardByStudentNo = () => {
    const {studentNo, actionName4} = this.state;
    if (studentNo) {
      StudentDao.getIdCardByStudentNo(studentNo, result => {
        this.setState({idCardTitle: [actionName4], studentNo: '', idCardList: [result]});
        ToastAndroid.show('查询成功', ToastAndroid.SHORT);
      });
    } else {
      ToastAndroid.show('请输入要查询的学号', ToastAndroid.SHORT);
    }
  };

  _insertStudent = () => {
    StudentDao.randomAddStudent(success => {
      ToastAndroid.show('插入成功', ToastAndroid.SHORT);
    });
  };

  _render() {
    const {
      studentList,
      idCardList,
      title,
      idCardTitle,
      studentKey,
      studentNo,
      actionName1,
      actionName2,
      actionName3,
      actionName4,
    } = this.state;
    return (
        <View style={styles.container}>
          <ScrollView contentContainerStyle={styles.contentContainer}>
            <Card style={styles.cardViewWrap}>
              <Card.Title title="学生查询结果" subtitle={title}/>
              <Card.Content>
                {studentList.map(valueObj => {
                  return <View style={styles.itemViewWrap} key={valueObj.id}>
                    <Text style={styles.itemText}>id：{valueObj.id}</Text>
                    <Text style={styles.itemText}>学号：{valueObj.studentNo}</Text>
                    <Text style={styles.itemText}>电话：{valueObj.telPhone}</Text>
                    <Text style={styles.itemText}>性别：{valueObj.sex}</Text>
                    <Text style={styles.itemText}>姓名：{valueObj.name}</Text>
                    <Text style={styles.itemText}>地址：{valueObj.address}</Text>
                    <Text style={styles.itemText}>学校名字：{valueObj.schoolName}</Text>
                    <Text style={styles.itemText}>年级：{valueObj.grade}</Text>
                  </View>;
                })}
              </Card.Content>
            </Card>
            <Card style={styles.cardViewWrap}>
              <Card.Title title="IdCard查询结果" subtitle={idCardTitle}/>
              <Card.Content>
                {idCardList.map(valueObj => {
                  return <View style={styles.itemViewWrap} key={valueObj.id}>
                    <Text style={styles.itemText}>id：{valueObj.id}</Text>
                    <Text style={styles.itemText}>持有者姓名：{valueObj.userName}</Text>
                    <Text style={styles.itemText}>身份证号码：{valueObj.idNo}</Text>
                  </View>;
                })}
              </Card.Content>
            </Card>

            <Card style={styles.cardViewWrap}>
              <Card.Title title="增"/>
              <Card.Actions>
                <Button mode="outlined" color={Colors.black} onPress={this._insertStudent}>插入一个学生数据</Button>
              </Card.Actions>
            </Card>

            <Card style={styles.cardViewWrap}>
              <Card.Title title="查"/>
              <Card.Content style={styles.searchContentView}>
                <TextInput
                    style={styles.textField}
                    placeholder={'根据学生Id查询IDCard'}
                    keyboardType={'default'}
                    maxLength={20}
                    onSubmitEditing={this._getIdCardByStudentId}
                    onChangeText={(text) => {
                      this.setState({studentKey: text});
                    }}
                    value={studentKey}
                />
                <TextInput
                    style={styles.textField}
                    placeholder={'根据学号查询IDCard'}
                    keyboardType={'default'}
                    maxLength={20}
                    onSubmitEditing={this._getIdCardByStudentNo}
                    onChangeText={(text) => {
                      this.setState({studentNo: text});
                    }}
                    value={studentNo}
                />
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._getAllStudent}>{actionName1}</Button>
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._getAllIdCard}>{actionName3}</Button>
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._getIdCardByStudentId}>{actionName2}</Button>
                <Button mode="outlined" style={styles.buttonStyle} color={Colors.black}
                        onPress={this._getIdCardByStudentNo}>{actionName4}</Button>
              </Card.Content>
            </Card>
          </ScrollView>
        </View>
    );
  }
}

export default Home2;

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
    flexWrap: 'wrap',
    borderBottomWidth: StyleSheet.hairlineWidth,
    borderBottomColor: '#000000',
    paddingVertical: 5,
  },
  itemText: {
    fontSize: 14,
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
