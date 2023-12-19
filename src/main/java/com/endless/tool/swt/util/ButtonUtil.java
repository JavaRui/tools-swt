package com.endless.tool.swt.util;
import com.endless.tool.swt.mgr.ChooseDlgMgr;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import java.io.IOException;

/**
 * 2015-4-13
 * @author cr.wu
 *
 */
public class ButtonUtil {
	
	private ButtonUtil(){
		
	}
	/**
	 * 创建一个选择路径的button
	 * */
	public static Button createDirBtn(Composite parent , final INBack callBack){
		Button tempBtn = new Button(parent, SWT.PUSH);
		tempBtn.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				String path = ChooseDlgMgr.openDir();
				if(path == null){
					return;
				}
				callBack.callBack(path);
			}
		});
		tempBtn.setText("选择路径");
		return tempBtn;
	}
	
	
	/**
	 * 创建文件夹选择按钮
	 * @param parent
	 * @param backText
	 * @return
	 */
	public static Button createDirBtn(Composite parent , final Text backText){
		return createDirBtn(parent , new INBack() {
			
			@Override
			public void callBack(Object o) {
				backText.setText(o+"");
			}
		});
		
	}
	
	/**
	 * 创建一个选择文件的button
	 * */
	public static Button createFileBtn(Composite parent , final INBack callBack){
		Button tempBtn = new Button(parent, SWT.PUSH);
		tempBtn.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				String path = ChooseDlgMgr.openFile();
				if(path == null){
					return;
				}
				callBack.callBack(path);
			}
		});
		tempBtn.setText("选择路径");
		return tempBtn;
	}
	
	/**
	 * 创建一个选择文件的按钮，和backText绑定，选择的文件路径会显示到backText去
	 * */
	public static Button createFileBtn(Composite parent , final Text backText){
		return createFileBtn(parent , new INBack() {
			
			@Override
			public void callBack(Object o) {
				backText.setText(o+"");
			}
		});
		
	}
	
	/**
	 * 创建一个选择颜色的button
	 * */
	public static Button createColorBtn(Composite parent , final String btnText, final INBack callBack){
		Button tempBtn = new Button(parent, SWT.PUSH);
		tempBtn.setText(btnText);
		tempBtn.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				RGB rgb = ChooseDlgMgr.openColor();
				if(rgb == null){
					return;
				}
				callBack.callBack(rgb);
			}
		});
		return tempBtn;
	}
	/**
	 * 创建一个打开文件或文件夹的按钮
	 * */
	public static Button createOpenFileBtn(Composite parent , final String btnText,final String filePath){
		Button openDataBtn = new Button(parent, SWT.PUSH);
		openDataBtn.setText(btnText);
		openDataBtn.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				openEx(filePath);
			}
			
		});
		return openDataBtn;
	}

	public static void openEx(String filePath) {
		try {
			Runtime.getRuntime().exec("explorer " + filePath);
		} catch (IOException var2) {
			var2.printStackTrace();
		}

	}

	
}

