package android.jrichardsz.org.myapplication;

import android.common.ResourceUtils;
import android.graphics.Bitmap;
import android.util.Log;

import android.jrichardsz.org.myapplication.R;
import min3d.Shared;
import min3d.Utils;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.parser.IParser;
import min3d.parser.Parser;
import min3d.vos.Light;
import min3d.vos.TextureVo;

/**
 * Created by Glup on 4/01/16.
 */
public class Short3DView2 extends RendererActivity {
    private Object3dContainer objModel;
    private int count;
    private TextureVo texture;

    @Override
    public void initScene() {

        scene.lights().add(new Light());

        IParser parser = null;

        try{
            parser = Parser.createParser(Parser.Type.OBJ,
                    getResources(),ResourceUtils.getGlobalResourcePackageIdentifier(this.getBaseContext())+":raw/m_b_short_standar_corta_cadera_11_obj", true);
        }catch(Exception ex){
            Log.e("HORROR",ex.getMessage());
        }

        parser.parse();

        objModel = parser.getParsedObject();
        objModel.scale().x = objModel.scale().y = objModel.scale().z = 1.5f;
        objModel.position().y=-1;
        scene.addChild(objModel);


        Bitmap b = Utils.makeBitmapFromResourceId(R.drawable.textura_prueba);
        Shared.textureManager().addTextureId(b, "sam_0811__texturaprueba");
        b.recycle();
        //texture = new TextureVo("sam_0811__texturaprueba");
        //objModel.textures().clear();
        //objModel.textures().add(texture);
        loadAllTextures();

        count = 0;
    }

    private void loadAllTextures() {
        int numChildren = objModel.numChildren();
        Log.e("NumHijos:", numChildren + "");
        objModel.getChildAt(0).textures().clear();
        objModel.getChildAt(0).pointSmoothing(true);
        objModel.getChildAt(0).shadeModel();
        objModel.getChildAt(0).colorMaterialEnabled();
        objModel.getChildAt(0).lineSmoothing(true);
        objModel.getChildAt(0).doubleSidedEnabled(true);
        objModel.getChildAt(0).textures().addById("sam_0811__texturaprueba");
    }

    @Override
    public void updateScene() {
		/*count++;
		// Assign a different texture to the two objects
		// every second or so
		if (count % 240 == 0) {
			objModel.textures().clear(); // ie, no texture
		}
		else if (count % 240 == 60) {
			objModel.textures().addReplace(texture);
		}
		else if (count % 240 == 120) {
			objModel.textures().addReplace(texture);
		}
		else if (count % 240 == 180) {
			objModel.textures().addReplace(texture);
		}*/

        objModel.rotation().y++;

    }
}