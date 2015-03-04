package jp.egaonohon.activity.list.multiselect;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * ラジオボタンの時と同じく、レイアウトファイルの方に秘密がある。
 *
 * 実はレイアウトファイル（activity_main.xml)に秘密がある。リストビューの中に
 * android:choiceMode="multipleChoice" と記述するだけ!
 *
 * ちなみに、このオプションを記述しないときは、android:choiceMode="none"と同じ。
 *
 * @author 1107AND
 *
 */
public class MainActivity extends Activity {
	Activity activity;
	ArrayAdapter<String> adapter;
	ListView list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<String> data = new ArrayList<String>();
		data.add("胡椒");
		data.add("ターメリック");
		data.add("コリアンダー");
		data.add("生姜");
		data.add("ニンニク");
		data.add("サフラン");
		data.add("砂糖");
		data.add("はちみつ");
		data.add("ごま油");
		data.add("岩塩");
		data.add("豆板醤");
		data.add("パセリ");
		data.add("卵");
		data.add("白米");
		data.add("味噌");
		data.add("醤油");
		data.add("ゴマ");
		data.add("銀杏");

		/**
		 * リストビューを扱うときには、リストビュー以外のコンボボックスなども使う可能性があるため、共通のインターフェースを持たせない。
		 * その時に用いるのがArrayAdapter。アレイリストとリストビューは直接繋げない。
		 *
		 * 【ArrayAdapterをnewする時の引数について】 第1引数の this はコンテキスト。 第2引数
		 * android.R.layout.simple_list_item_1
		 * は、一行のフォーマットを示す。ここの、android.R.layout.simple_list_item_1は、
		 * Androidがデフォルトで備えるレイアウトを示している。SDKの中にあるでしょうが。 第3引数 data
		 * は、表示するテキストのリスト。今回は、アレイリスト。
		 */
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, data);
		list = (ListView) findViewById(R.id.list);
		/**
		 * setAdapter()メソッドで、ArrayAdapterをレイアウト上のListViewにセットする。
		 * そうすることでアダプター経由でデータと1行のフォーマットをリストビューに設定。
		 */
		list.setAdapter(adapter);

		/**
		 * setOnItemClickListenerを実装するとある一行をクリックした時のアクションを実装できるようになる。
		 * クリック可能なリストを作る感じ。
		 *
		 * ちなみに、長押しの時に処理を走らせるのは… setOnItemLongClickListener …となる。
		 */
		list.setOnItemClickListener(
		new AdapterView.OnItemClickListener() {
			/**
			 * implementsするメソッドがonItemClick()。
			 *
			 * 第1引数は、リストビューの参照（AdapterView<?> av）。<?>となっているがリストビューの参照。
			 * 第2引数は、クリックされた行の参照。今回は実質的にはテキストビュー。
			 * 第3引数は、アダプタの何番目の行に相当するか。それがposition。 第4引数は、idとは、今は使っていないが、行ごとのid番号。
			 */
			public void onItemClick(AdapterView<?> av, View view, int position,
					long id) {
				String msg = "選択したのは、";

				/**
				 * チェックボックスが選択されたら、 getChildCountでリストビューの長さを取得
				 * リストビューの行をlist.getChildAt(i)で1つずつ引っ張ってきて
				 * チェックされているなら　check.isChecked　、メッセージを足し込んでいく。check.getText()
				 */

				for (int i = 0; i < list.getChildCount(); i++) {
					CheckedTextView check = (CheckedTextView) list
							.getChildAt(i);
					if (check.isChecked()) {
						msg += check.getText() + ",";
					}
				}
				/**
				 * substring()メソッドで、文字列の最初（0）からメッセージの長さから-1番目までを切り出すことで、「,」
				 * を削除している。
				 */
				msg = msg.substring(0, msg.length() - 1);
				Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG)
						.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
