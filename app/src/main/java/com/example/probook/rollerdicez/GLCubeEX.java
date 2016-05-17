package com.example.probook.rollerdicez;



        import android.app.Activity;
        import android.content.Context;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.opengl.GLSurfaceView;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.Random;

        import javax.microedition.khronos.opengles.GL10;

/**
 * Created by ProBook on 5/15/2015.
 */
public  class GLCubeEX extends Activity  {
    GLSurfaceView ourSurface;
    static int myResult;


    Button mButton;
    @Override


    protected void onCreate(Bundle ss) {

        super.onCreate(ss);

        ourSurface = new GLSurfaceView(this);
        ourSurface.setRenderer(new MyGLRenderer(this));

        setContentView(R.layout.activity_glcube_ex);
        this.ourSurface = (GLSurfaceView) this.findViewById(R.id.glSurface);
        ourSurface.setRenderer(new MyGLRenderer(this));




        myResult = 5 ;
        mButton = (Button)findViewById(R.id.button01);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MyGLRenderer.angleCube = -800.0f;

                myResult = new Random(System.currentTimeMillis()).nextInt(6)+1;



            }

        });
    }







    public void stopRotation(View stopButtonView)
    {
        Button stopButton = (Button)stopButtonView;
        String text = stopButton.getText().toString();
        if (text.equalsIgnoreCase("stop"))
        {

            stopButton.setText("Start");
        }
        else
        {

            stopButton.setText("Stop");
        }
    }

    protected void onResume() {

        super.onResume();
        ourSurface.onResume();
        // Add the following line to register the Session Manager Listener onResume
    }

    @Override

    protected void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        super.onPause();
        ourSurface.onPause();
    }
}
