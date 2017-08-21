package com.example.qthjen.onflinggetsture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    int arrayImage[] = {R.drawable.mywifeont, R.drawable.mywifetwo, R.drawable.mywifefour, R.drawable.mywifefive};
    int position = 0;
    GestureDetector gestureDetector;
    int SWIPE_THRESHOLD = 100; // khoảng cách tối thiếu khi vuốt
    int SWIPE_VELOCITY_THRESHOLD = 100; // tốc độ tối thiểu khi vuốt

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        image.setImageResource(arrayImage[position]);

        gestureDetector = new GestureDetector(this, new MyGesture());
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

    }

    class MyGesture extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            /** khi lần đầu tiên chạm vào màn hình vị trí chạm sẽ trả về e1 và kéo đến 1 vị trí rồi thả tay
             * ra nó sẽ trả về vị trí e2
             * và khi kéo theo chiều ngang 1 tốc độ nào đó nó sẽ trả về velocityX và tôc độ chiều dọc
             * là velocityY **/

            /** kéo từ trái sang phải **/
            if ( (e2.getX() - e1.getX()) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                Toast.makeText(MainActivity.this, "Left to Right", Toast.LENGTH_SHORT).show();

                position--;
                if ( position < 0) {
                    position = arrayImage.length - 1;
                }
                image.setImageResource(arrayImage[position]);

            }

            /** kéo từ phải sang trái **/
            if ( (e1.getX() - e2.getX() ) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {

                Toast.makeText(MainActivity.this, "Right to Left", Toast.LENGTH_SHORT).show();

                position++;
                if ( position > (arrayImage.length - 1)) {
                    position = 0;
                }
                image.setImageResource(arrayImage[position]);

            }

            /** kéo từ dưới lên trên **/
            if ( (e1.getY() - e2.getY() ) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {

                Toast.makeText(MainActivity.this, "Bottom to Top", Toast.LENGTH_SHORT).show();
            }

            /** kéo từ  trên xuống dưới **/
            if ( (e2.getY() - e1.getY() ) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {

                Toast.makeText(MainActivity.this, "Top to Bottom", Toast.LENGTH_SHORT).show();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }



}
