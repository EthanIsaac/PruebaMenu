package mx.itesm.eibt.pruebamenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.event.ActionListener;

/**TIPS
Para correrlo en escritorio, abrimos la terminal y usamos el comando gradlew desktop:run
*/

/**
 * Created by ethan on 31/01/2017.
 */
public class PantallaMenu implements Screen {
    private static final float ANCHO = 1280;
    private static final float ALTO = 800;
    private final Menu menu;

    //Cámara y vista
    private OrthographicCamera camara;
    private Viewport vista;

    //Texturas
    private Texture texturaFondo;
    private Texture texturaButtonPlay;

    //SpriteBatch
    private SpriteBatch batch; //Hace trazos en la pantalla

    //Escenas
    private Stage escenaMenu;

    public PantallaMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void show() {
        crearCamara();
        cargarTexturas();
        crearObjetos();
    }

    private void crearObjetos() {
        batch = new SpriteBatch();
        escenaMenu = new Stage(vista,batch); //Hay que decirle qué tamaño va a tener y cómo se va a escalar
        Image imgBackground = new Image(texturaFondo);
        escenaMenu.addActor(imgBackground);
        //Botón
        TextureRegionDrawable trdButtonPlay = new TextureRegionDrawable(new TextureRegion(texturaButtonPlay)); // TextureRegionDrawable no puede crearse con una textura como parámetro, llamamos a Texture region para eso.
        ImageButton buttonPlay = new ImageButton(trdButtonPlay);
        buttonPlay.setPosition(ANCHO/2-buttonPlay.getWidth()/2,3*ALTO/4-buttonPlay.getHeight()/2);
        escenaMenu.addActor(buttonPlay);

        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("clicked","Me hicieron click"); //"Dónde estoy", "mensaje que quiero mostrar"
                menu.setScreen(new PantallaAcercaDe(menu));
            }
        });
        Gdx.input.setInputProcessor(escenaMenu); //Quién se encarga de procesar las entradas
        Gdx.input.setCatchBackKey(false);
    }

    private void cargarTexturas() {
        texturaFondo = new Texture("Background2.jpg");
        texturaButtonPlay = new Texture("playButton.png");
    }

    private void crearCamara() {
        camara = new OrthographicCamera(ANCHO, ALTO);
        camara.position.set(ANCHO/2,ALTO/2,0);
        camara.update();
        vista = new StretchViewport(ANCHO,ALTO,camara);
    }

    @Override
    public void render(float delta) { //Delta nos da la fracción de tiempo que dura un frame
        borrarPantalla();
        escenaMenu.draw(); //Hay que dibujar los elementos que se agregaron
    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(0,0,0,1); //gl es el objeto que representa el elemento que pinta con OpenGL
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Para llenar completamente los bits
    }

    @Override
    public void resize(int width, int height) {
        vista.update(width, height);
    }

    @Override
    public void pause() {
// PRUEBA COMMIT
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
