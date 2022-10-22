import 'package:flutter/material.dart';
import 'package:path_manager/custom_widgets/Button.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  void onClick() {
    print('hihi');
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: const BoxDecoration(
        color: Colors.black
      ),
      child: Column(children: [
        Row(
          children: [Button("Among us", onClick, Alignment.center)],
        ),
        Row(
          children: [Button("Among us", onClick, Alignment.center)],
        ),
        Row(
          children: [Button("Among us", onClick, Alignment.center)],
        )
      ])
    );
  }
}
