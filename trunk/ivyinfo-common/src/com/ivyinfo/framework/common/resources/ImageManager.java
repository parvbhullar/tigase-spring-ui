
package com.ivyinfo.framework.common.resources;

import com.ivyinfo.framework.common.exception.IvyinfoException;
import com.ivyinfo.framework.common.file.ResouceLoader;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;


public class ImageManager {

	private static HashMap<String, ImageIcon> imageRegistry = new HashMap<String, ImageIcon>();

	public static HashMap<String, ImageIcon> getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new HashMap<String, ImageIcon>();
		}
		return imageRegistry;
	}

	public static ImageIcon getImageIcon(String imageName) throws IvyinfoException {
		return getImageIcon(imageName, null);
	}

	public static ImageIcon getImageIcon(String imageName, String description) throws IvyinfoException {
		ImageIcon getImageIcon = imageRegistry.get(imageName);
		if (getImageIcon == null) {
			getImageIcon = description == null ? new ImageIcon(ResouceLoader
					.getResouce(imageName)) : new ImageIcon(ResouceLoader
					.getResouce(imageName), description);
			imageRegistry.put(imageName, getImageIcon);
		}
		return getImageIcon;
	}

	public static Image getImage(String imageName) throws IvyinfoException {
		return getImageIcon(imageName).getImage();
	}
	
	/**
	 * use "resource/image" as the default directory
	 * 
	 * @param imageName
	 * 
	 * @return ImageIcon
	 * */
	public static ImageIcon getImageIconByShortName(String imageName) throws IvyinfoException {
		return getImageIcon("com/ivyinfo/app/resource/image/" + imageName);
	}
	
	/**
	 * use "resource/image" as the default directory
	 * 
	 * @param imageName
	 * 
	 * @return Image
	 * */
	public static Image getImageByShortName(String imageName) throws IvyinfoException {
		return  getImageIcon("com/ivyinfo/app/resource/image/" + imageName).getImage();
	}
}
