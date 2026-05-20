### 2026-05-20

- 実装した部分
  - BasicTextFieldにbuttonとonClickでの保存機能追加

- 追加した機能
  - 保存機能
  - 固定ファイル名での保存（名前を付けて保存はまだできない）

- 学んだこと
  - composeは状態をベースとしている
  - 保存するときは基本カレンディレクトリのdesktopApp\Main.ktを基準としている。
  - コードの意味
    ( onClick = {FIle("memo.txt").writeText(text)}){Text("保存")}
    ( onClick ...) ... クリックしたときの一連の処理
    FIle("memo.txt") ... memo.txtという名前のファイルオブジェクト
    .writeText(text) ... ローカル変数text(BsicTextFIle内のデータ)を文字列として書き込む
    {Text("保存")} ... テキストボックスに「保存」と表示したbutton
  - {}で機能を囲む。

- 感想
  - UIをメソッドで決めるんだ！　おもしろ！
  - OOPをこんなに簡単に実装できるとは。すげえ。
  - compose desktopってお手ごろだなあ
