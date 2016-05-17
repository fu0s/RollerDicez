package com.example.probook.rollerdicez;

/**
 * Created by ProBook on 5/27/2015.
 */


        import javax.microedition.khronos.egl.EGLConfig;
        import javax.microedition.khronos.opengles.GL10;
        import android.content.Context;
        import android.opengl.GLSurfaceView.Renderer;
        import android.opengl.GLU;


        import static java.lang.Math.abs;
        import static java.lang.Math.cos;
        import static java.lang.Math.sin;
        import static java.lang.Math.tan;

public class MyGLRenderer implements Renderer {
    private PhotoCube cube;     // (NEW)
    static float angleCube = -800.0f;     // rotational angle in degree for cube
     static float speedCube = 15.0f; // rotational speed for cube
    float x = 1.57f;
    float angleRotation ;

    int axeRotaionX = 0 ;
    int axeRotaionY = 0 ;
    int AxeRotaionZ = 0 ;

    public float axeTranslationX() {
        if (angleCube < angleRotation) {
            int i=1 ;

           x+= 0.1f;
          //  if(x ==){    //x + pi/2 mod 2 pi == 0 sachant que x commence de -pi/2 (x +1.57) % 6.28)
           //    i=-1*i;
          //}
            return (float) (2*i*cos(x));


        } else {
            return 0.0f;
        }
    }

    public float axeTranslationY() {
        if (angleCube < angleRotation) {



            return (float) (2*cos(x));


        } else {
            return 0.0f;
        }
    }
    public float axeTranslationZ() {
        if (angleCube < angleRotation) {



            return (float) (-15.0f +2*(cos(x)));


        } else {
            return -15.0f;
        }
    }

    public float angleCube(){

        switch(GLCubeEX.myResult){

            case 1:
                angleRotation = 0.0f;
                axeRotaionX = 0 ;
                axeRotaionY = 0 ;
                AxeRotaionZ = 1 ;
                break;

            case 2:
                angleRotation = 90.0f;
                axeRotaionX = 0 ;
                axeRotaionY = 1 ;
                AxeRotaionZ = 0 ;
                break;

            case 3:
                angleRotation = 180.0f;
                axeRotaionX = 1 ;
                axeRotaionY = 0 ;
                AxeRotaionZ = 0 ;
                break;

            case 4:
                angleRotation = 270.0f;
                axeRotaionX = 0 ;
                axeRotaionY = 1 ;
                AxeRotaionZ = 0 ;
                break;

            case 5:
                angleRotation = 90.0f;
                axeRotaionX = 1 ;
                axeRotaionY = 0 ;
                AxeRotaionZ = 0 ;

                break;

            case 6:
                angleRotation = 270.0f;
                axeRotaionX = 1 ;
                axeRotaionY = 0 ;
                AxeRotaionZ = 0 ;
                break;



        }
        if (angleCube <angleRotation) {
            return angleCube += speedCube ;


        }
        else {
            return angleRotation;

        }
    }



    public float axeCubeX(){
        if (angleCube <angleRotation) {
            return 5.0f;

        }
        else { if (axeRotaionX == 0) {
            return 0.0f;
        }
        else {return 1.0f;}
        }
    }


    public float axeCubeY(){
        if (angleCube <angleRotation) {
            return 5.0f;

        }
        else {
            if (axeRotaionY == 0) {
                return 0.0f;
            }
            else {return 1.0f;}
        }

    }

    public float axeCubeZ(){
        if (angleCube <angleRotation) {
            return 5.0f;

        }
        else {
            if (AxeRotaionZ == 0) {
                return 0.0f;
            }
            else {return 1.0f;}
        }

    }



    // Constructor

    public MyGLRenderer(Context context) {
        cube = new PhotoCube(context);    // (NEW)
    }

    // Call back when the surface is first created or re-created.
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.5f, 0.0f, 0.1f,0.0f);  // Set color's clear-value to black
        gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest
        gl.glEnable(GL10.GL_DEPTH_TEST);   // Enables depth-buffer for hidden surface removal
        gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  // nice perspective view
        gl.glShadeModel(GL10.GL_SMOOTH);   // Enable smooth shading of color
        gl.glDisable(GL10.GL_DITHER);      // Disable dithering for better performance

        // Setup Texture, each time the surface is created (NEW)
        cube.loadTexture(gl);             // Load images into textures (NEW)
        gl.glEnable(GL10.GL_TEXTURE_2D);  // Enable texture (NEW)
    }

    // Call back after onSurfaceCreated() or whenever the window's size changes.
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) height = 1;   // To prevent divide by zero
        float aspect = (float)width / height;

        // Set the viewport (display area) to cover the entire window

        gl.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
        gl.glLoadIdentity();                 // Reset projection matrixv
        // Use perspective projection
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
        gl.glLoadIdentity();                 // Reset

        // You OpenGL|ES display re-sizing code here
        // ......
    }

    // Call back to draw the current frame.
    @Override
    public void onDrawFrame(GL10 gl) {
        // Clear color and depth buffers
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // ----- Render the Cube -----


        gl.glLoadIdentity();                  // Reset the model-view matrix
        gl.glTranslatef(axeTranslationX(),axeTranslationY(),axeTranslationZ());  // Translate into the screen
        gl.glRotatef(angleCube(), axeCubeX(),axeCubeY(),axeCubeZ()); // Rotate
        cube.draw(gl);


    }
}
