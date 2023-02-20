import 'package:flutter/material.dart';

class Button extends StatefulWidget {
  String text;
  Function() onClick;
  AlignmentGeometry alignment;

  Button(this.text, this.onClick, this.alignment);


  @override
  State<Button> createState() => _ButtonState();
}

class _ButtonState extends State<Button> {
  @override
  Widget build(BuildContext context) {
    return TextButton(
      onPressed: () => widget.onClick(),
      child: Text(widget.text),
      style: ButtonStyle(
        alignment: widget.alignment
      ),
    );
  }
}
