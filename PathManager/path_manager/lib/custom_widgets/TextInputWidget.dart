import 'package:flutter/material.dart';


class TextInputWidget extends StatefulWidget {
  String text;
  Icon icon;

  TextInputWidget(this.text, this.icon);

  @override
  State<TextInputWidget> createState() => _TextInputWidgetState();
}

class _TextInputWidgetState extends State<TextInputWidget> {

  final con = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return TextField(
      controller: con,
      decoration: InputDecoration(
        labelText: widget.text,
        prefixIcon: widget.icon
      ),
    );
  }
}
