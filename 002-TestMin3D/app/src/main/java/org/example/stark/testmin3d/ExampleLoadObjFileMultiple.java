package org.example.stark.testmin3d;

import android.common.ResourceUtils;
import android.common.UIUtils;

import min3d.core.Object3d;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.parser.IParser;
import min3d.parser.Parser;
import min3d.vos.Light;
import org.example.stark.testmin3d.R;

public class ExampleLoadObjFileMultiple extends RendererActivity 
{
	private final float MAX_ROTATION = 40;
	private final float MAX_CAM_X = 6f;
	private Object3dContainer car;
	private Object3d tireRR;
	private Object3d tireRF;
	private Object3d tireLR;
	private Object3d tireLF;
	private int rotationDirection;
	private float camDirection;

	@Override
	public void initScene() {

		IParser parser = null;

		try{
			parser = Parser.createParser(Parser.Type.MAX_3DS,
					getResources(), ResourceUtils.getGlobalResourcePackageIdentifier(this.getBaseContext())+":raw/camaro2_obj", true);
		}catch(Exception ex){
			UIUtils.showSimpleErrorDialog(this, "Fatal Error", ex);
		}

		car = parser.getParsedObject();
		scene.addChild(car);
		
		tireRR = car.getChildByName("tire_rr");
		tireRF = car.getChildByName("tire_rf");
		tireLR = car.getChildByName("tire_lr");
		tireLF = car.getChildByName("tire_lf");

		tireLF.position().x = -.6f;
		tireLF.position().y = 1.11f;
		tireLF.position().z = .3f;
		
		tireRF.position().x = .6f;
		tireRF.position().y = 1.11f;
		tireRF.position().z = .3f;
		
		tireRR.position().x = .6f;
		tireRR.position().y = -1.05f;
		tireRR.position().z = .3f;

		tireLR.position().x = -.6f;
		tireLR.position().y = -1.05f;
		tireLR.position().z = .3f;

		car.rotation().x = -90;
		car.rotation().z = 180;

		scene.camera().position.x = MAX_CAM_X;
		scene.camera().position.z = 3.5f;
		scene.camera().position.y = 3.5f;

		Light light = new Light();
		light.position.setAllFrom(scene.camera().position);
		scene.lights().add(light);
		
		rotationDirection = 1;
		camDirection = -.01f;
	}

	@Override
	public void updateScene() {
		tireRF.rotation().z += rotationDirection;
		tireLF.rotation().z += rotationDirection;
		
		if(Math.abs(tireRF.rotation().z) >= MAX_ROTATION)
			rotationDirection = -rotationDirection;
		
		scene.camera().position.x += camDirection;
		scene.lights().get(0).position.setAllFrom(scene.camera().position);
		
		if(Math.abs(scene.camera().position.x) >= MAX_CAM_X)
			camDirection = -camDirection;
	}
}