package android.jrichardsz.org.myapplication;

import android.common.ResourceUtils;
import android.util.Log;
import android.widget.FrameLayout;

import android.jrichardsz.org.myapplication.R;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.parser.IParser;
import min3d.parser.Parser;
import min3d.vos.Light;

/**
 * Created by Glup on 5/01/16.
 */
public class Short3DView3 extends RendererActivity{
    private Object3dContainer prenda;
    private final float CAM_RADIUS_X = 20;
    private final float CAM_RADIUS_Y = 15;
    private final float CAM_RADIUS_Z = 30;
    private final float ROTATION_SPEED = 1;
    private float degrees;

    @Override
    protected void onCreateSetContentView() {
        setContentView(R.layout.custom_layout);
        FrameLayout fl = (FrameLayout) this.findViewById(R.id.frame2);
        fl.addView(_glSurfaceView);
    }
    @Override
    public void initScene(){
        scene.lights().add(new Light());

        IParser parser = null;

        try{
            parser = Parser.createParser(Parser.Type.MAX_3DS,
                    getResources(), ResourceUtils.getGlobalResourcePackageIdentifier(this.getBaseContext())+":raw/sam_0811", false);
        }catch(Exception ex){
            Log.e("HORROR",ex.getMessage());
        }

        parser.parse();

        prenda = parser.getParsedObject();
        prenda.scale().x=prenda.scale().y=prenda.scale().z=1.5f;
        prenda.position().x=0;
        prenda.position().y=-1;
        scene.addChild(prenda);
        prenda.getChildAt(0).doubleSidedEnabled(true);

        //scene.camera().target=prenda.position();
    }

    private void loadAllTextures() {
        int numChildren = prenda.numChildren();
        Log.e("NumHijos:", numChildren + "");
        //prenda.getChildAt(0).textures().clear();
        //prenda.getChildAt(0).pointSmoothing(true);
        prenda.getChildAt(0).doubleSidedEnabled(true);
        //prenda.getChildAt(0).textures().addById("sam_0811__texturaprueba");
    }
    @Override
    public void updateScene(){
        /*float radians = degrees * ((float)Math.PI / 180);

        scene.camera().position.x = (float)Math.cos(radians) * CAM_RADIUS_X;
        scene.camera().position.y = (float)Math.sin(radians) * CAM_RADIUS_Y;
        scene.camera().position.z = (float)Math.sin(radians) * CAM_RADIUS_Z;

        degrees += ROTATION_SPEED;*/
        prenda.rotation().y++;
    }
}
