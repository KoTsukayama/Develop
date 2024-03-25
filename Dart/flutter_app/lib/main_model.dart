import 'package:flutter/material.dart';

class MainModel extends ChangeNotifier{
  int a = 2;
  void change(){
    a = 2;

    // 値の変更を通知
    notifyListeners();
  }
}