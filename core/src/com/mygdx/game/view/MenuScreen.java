package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.MyGame;
import com.mygdx.game.model.Spaceship;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class MenuScreen extends MyScreen {
    private TextureAtlas textures, chars;
    private Button lastPressedDiff;
    private Button lastPressedShip;
    private MyGame.Difficulties chosenDiff;
    private Spaceship.Ships chosenPlayer;
    private Button start;
    private TextureRegion logo;
    private TextureRegion selectDiff;
    private int type;
    private Button veryEasy, easy, medium, hard, veryHard;
   private Spaceship.Ships ship;
   private MyGame.Difficulties diff;
    private Stage stage;
    private Label current;
   private Button[]butts;
   private Button[] ships;
   private Button red, blue, green;
    public MenuScreen(MyGame game) {
        super(game);
       textures =game.getLabels();
       chars = game.getProtagonists();
       start = new Button(textures.findRegion("startButton_released"), textures.findRegion("startButton_pressed"), 0-11f, -16f, 22f, 5f);

        veryEasy =new Button(textures.findRegion("veryeasy1"), textures.findRegion("veryeasy2"), 0-5.67f, 6f, 2f*5.67f, 2f, MyGame.Difficulties.VERY_EASY);
        easy=new Button(textures.findRegion("easy2"), textures.findRegion("easy1"), 0-2.61f, 4-0.5f, 2f*2.61f, 2f, MyGame.Difficulties.EASY);
        medium=new Button(textures.findRegion("medium1"), textures.findRegion("medium2"), 0-4.03f, 2-1f, 2f*4.03f, 2f, MyGame.Difficulties.MEDIUM);
        hard=new Button(textures.findRegion("hard2"), textures.findRegion("hard1"), 0-2.97f, -1.5f, 2f*2.97f, 2f, MyGame.Difficulties.HARD);
        veryHard=new Button(textures.findRegion("veryhard2"), textures.findRegion("veryhard1"), 0-5.9f, -4f, 2f*5.9f, 2f, MyGame.Difficulties.VERY_HARD);
        chosenDiff = MyGame.Difficulties.MEDIUM;
        chosenPlayer = Spaceship.Ships.PLAYER_GREEN;
        butts =new Button[]{veryEasy, easy, medium, hard, veryHard};
        logo = new TextureRegion(textures.findRegion("Label_space_defenders"));
        selectDiff =  new TextureRegion(textures.findRegion("difficulty"));

        red = new Button(chars.findRegion("red"), chars.findRegion("redG"),0+2f , -9f, 3f, 3f, Spaceship.Ships.PLAYER_RED);
        blue= new Button(chars.findRegion("blue"), chars.findRegion("blueG"), 0-1.5f, -9f, 3f, 3f, Spaceship.Ships.PLAYER_BLUE);
       green = new Button(chars.findRegion("green"), chars.findRegion("greenG"),0-5f , -9f, 3f, 3f, Spaceship.Ships.PLAYER_GREEN);
        ships = new Button[]{red, green, blue};
        stage = new Stage();
        Skin s = new Skin(Gdx.files.internal("skin.json"));
        try {
            current = new Label("Best score: "+getHS(), s.get("default", Label.LabelStyle.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        current.setFontScale(0.5f);
        current.setPosition(Gdx.graphics.getWidth()/2f- current.getWidth()/4f, 1);
        stage.addActor(current);
    }

    @Override
    public void show() {
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        clearScreen();
        game.getBatch().setProjectionMatrix(camera.projection);
        game.getBatch().begin();
        game.getBatch().draw(bg, -viewportWidth / 2, -viewportHeight / 2, viewportWidth, viewportHeight);
        game.getBatch().draw(logo, 0-13f, viewportHeight/2-7.5f, 27f, 7f);
        game.getBatch().draw(selectDiff, 0-11f, viewportHeight/2-11.7f, 22f, 2f);
        for(int i = 0; i<butts.length; i++){
            butts[i].draw(game.getBatch(), butts[i].react(Gdx.input.getX(), Gdx.input.getY()));
        }
        for(int i = 0; i<ships.length; i++){
            ships[i].draw(game.getBatch(), ships[i].react(Gdx.input.getX(), Gdx.input.getY()));
        }
        handleControls();
        game.getBatch().end();
        stage.draw();
    }

    private void handleControls() {
     start.draw(game.getBatch(), start.react(Gdx.input.getX(), Gdx.input.getY()));
        if(Gdx.input.isTouched()) {
            if (start.react(Gdx.input.getX(), Gdx.input.getY())) {
               game.newGame(chosenDiff, chosenPlayer);
                return;
            }
                for(int i = 0; i<butts.length; i++){
                    if(butts[i].react(Gdx.input.getX(), Gdx.input.getY())) {
                        chosenDiff = butts[i].getDiff();
                        return;
                    }
            }
            for(int i = 0; i<ships.length; i++){
                if(ships[i].react(Gdx.input.getX(), Gdx.input.getY())) {
                    chosenPlayer = ships[i].getShip();
                    return;
                }
            }
        }
    }

    @Override
    public void dispose() {

    }

    class Button {

        private TextureRegion pressed;
        private TextureRegion released;
        private float xPos, yPos;
        private float width, height;
        private Spaceship.Ships ship;
        private MyGame.Difficulties diff;

        public Button(TextureRegion released, TextureRegion pressed, float x, float y, float width, float height) {
            this.pressed = pressed;
            this.released = released;
            xPos = x;
            yPos = y;
            this.width = width;
            this.height = height;

        }


        public Button(TextureRegion released, TextureRegion pressed, float x, float y, float width, float height, Spaceship.Ships ship) {
this( released,  pressed,  x,  y, width, height);
this.ship = ship;
        }

        public Spaceship.Ships getShip() {
            return ship;
        }

        public MyGame.Difficulties getDiff() {
            return diff;
        }

        public Button(TextureRegion released, TextureRegion pressed, float x, float y, float width, float height, MyGame.Difficulties diff){
            this( released,  pressed,  x,  y, width, height);
            this.diff = diff;
        }

        public boolean react(float x, float y){
            Vector3 coords =  camera.unproject(new Vector3(x, y, 0));
            if (coords.x >= xPos && coords.x <= xPos + width && coords.y >= yPos && coords.y <= yPos + height) {
                return true;
            }
            return false;
        }

        public void draw(SpriteBatch sb, boolean active) {
            if (active)
                sb.draw(pressed, xPos, yPos, width, height);
            else
                sb.draw(released, xPos, yPos, width, height);
        }
    }
    private static String getHS() throws Exception {

            FileInputStream fis = new FileInputStream("Highscore.txt");
            InputStreamReader isr = new InputStreamReader(fis, "CP1251");
            StringBuilder b = new StringBuilder();
            while (true) {
                int n = isr.read();
                if (n == -1)
                    break;
                b.append((char) n);
            }
            return b.toString();

    }
    public static void setHS(int hs) throws Exception{
        String best = getHS();
        if(Integer.parseInt(best)>=hs)
            return;
        String current =String.valueOf(hs);
                FileWriter wr = new FileWriter("Highscore.txt");
        wr.write(current);
        wr.close();
    }
}
