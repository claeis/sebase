package ch.softenvironment.view;

/* 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 */
 
/**
 * Define any behaviour for User Interface Handling (CUA).
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.4 $ $Date: 2004-09-14 16:56:57 $
 */
public abstract class CommonUserAccess {
//public static String MENU_VIEW_SYMBOLLIST_STANDARD = _resourceBundle_CUA.getString("MniViewSymbollistStandard_text");
	public static String getMncViewStatusbarText() {
		return getResource("MniViewStatusbar_text");
	}
//public static String MENU_VIEW_SYMBOLLISTS = _resourceBundle_CUA.getString("MnuViewSymbollists_text");
	public static String getMncViewSymbollistStandardText() {
		return getResource("MniViewSymbollistStandard_text");
	}
//public static String MENU_EDIT_CHANGE = _resourceBundle_CUA.getString("MnuEditChange_text");
	public static String getMniEditAssignText() {
		return getResource("MnuEditAssign");
	}
//public static String MENU_EDIT_ASSIGN = _resourceBundle_CUA.getString("MnuEditAssign");
	public static String getMniEditAssignWindowText() {
		return getResource("MnuEditAssignWindow");
	}
//public static String MENU_EDIT_CHANGE_WINDOW = _resourceBundle_CUA.getString("MnuEditChangeWindow_text");
	public static String getMniEditChangeText() {
		return getResource("MnuEditChange_text");
	}
//public static String MENU_EDIT_REMOVE = _resourceBundle_CUA.getString("MnuEditRemove_text");
	public static String getMniEditChangeWindowText() {
		return getResource("MnuEditChangeWindow_text");
	}
//public static String MENU_EDIT_CUT = _resourceBundle_CUA.getString("MnuEditCut_text");
	public static String getMniEditCopyText() {
		return getResource("MnuEditCopy_text");
	}
//public static String MENU_EDIT = _resourceBundle_CUA.getString("MnuEdit_text");
	public static String getMniEditCutText() {
		return getResource("MnuEditCut_text");
	}
//public static String MENU_EDIT_REDO = _resourceBundle_CUA.getString("MniEditRedo_text");
	public static String getMniEditFindReplaceText() {
		return getResource("MniEditFindReplaceWindow_text");
	}
//public static String MENU_EDIT_COPY = _resourceBundle_CUA.getString("MnuEditCopy_text");
	public static String getMniEditPasteText() {
		return getResource("MnuEditPaste_text");
	}
//public static String MENU_EDIT_UNDO = _resourceBundle_CUA.getString("MniEditUndo_text");
	public static String getMniEditRedoText() {
		return getResource("MniEditRedo_text");
	}
//public static String MENU_EDIT_RENAME = _resourceBundle_CUA.getString("MnuEditRename_text");
	public static String getMniEditRemoveText() {
		return getResource("MnuEditRemove_text");
	}
//public static String MENU_EDIT_SELECT_ALL = _resourceBundle_CUA.getString("MnuEditSelectAll_text");
	public static String getMniEditRenameText() {
		return getResource("MnuEditRename_text");
	}
//public static String MENU_EDIT_PASTE = _resourceBundle_CUA.getString("MnuEditPaste_text");
	public static String getMniEditSelectAllText() {
		return getResource("MnuEditSelectAll_text");
	}
//public static String MENU_EDIT_ASSIGN_WINDOW = _resourceBundle_CUA.getString("MnuEditAssignWindow");
	public static String getMniEditUndoText() {
		return getResource("MniEditUndo_text");
	}
//public static String MENU_EXTRAS = _resourceBundle_CUA.getString("MnuExtras_text");
	public static String getMniExtrasOptionsWindowsText() {
		return getResource("MniExtrasOptionsWindow_text");
	}
//public static String MENU_FILE = _resourceBundle_CUA.getString("MnuFile_text");
	public static String getMniFileExit() {
		return getResource("MniFileExit_text");
	}
//public static String MENU_FILE_IMPORT = _resourceBundle_CUA.getString("MniFileImport_text");
	public static String getMniFileExportText() {
		return getResource("MniFileExport_text");
	}
//public static String MENU_FILE_PRINT_WINDOW = _resourceBundle_CUA.getString("MnuFilePrintWindow_text");
	public static String getMniFileImportText() {
		return getResource("MniFileImport_text");
	}
//public static String MENU_FILE_EXIT = _resourceBundle_CUA.getString("MniFileExit_text");
	
	public static String getMniFileNewText() {
		return getResource("MnuFileNew_text");
	}
//public static String MENU_FILE_NEW = _resourceBundle_CUA.getString("MnuFileNew_text");
	public static String getMniFileNewWindowText() {
		return getResource("MnuFileNewWindow_text");
	}
//public static String MENU_FILE_SAVEAS = _resourceBundle_CUA.getString("MnuFileSaveAs_text");
	public static String getMniFileOpenWindowText() {
		return getResource("MniFileOpenWindow_text");
	}
//public static String MENU_FILE_OPEN_WINDOW = _resourceBundle_CUA.getString("MniFileOpenWindow_text");
	public static String getMniFilePrintWindowText() {
		return getResource("MnuFilePrintWindow_text");
	}
//public static String MENU_FILE_SAVE = _resourceBundle_CUA.getString("MnuFileSave_text");
	public static String getMniFileSaveAsText() {
		return getResource("MnuFileSaveAs_text");
	}
//public static String MENU_FILE_NEW_WINDOW = _resourceBundle_CUA.getString("MnuFileNewWindow_text");
	public static String getMniFileSaveText() {
		return getResource("MnuFileSave_text");
	}
//public static String MENU_FORMAT_FG_COLOR = _resourceBundle_CUA.getString("MnuFormatForegroundColor_text");
	public static String getMniFormatBgColorText() {
		return getResource("MnuFormatBackgroundColor_text");
	}
//public static String MENU_FORMAT_FONT = _resourceBundle_CUA.getString("MnuFormatFont_text");
	public static String getMniFormatFgColorText() {
		return getResource("MnuFormatForegroundColor_text");
	}
//public static String MENU_FORMAT_LINE_COLOR = _resourceBundle_CUA.getString("MnuFormatLineColor_text");
	public static String getMniFormatFillColorText() {
		return getResource("MnuFormatFillColor_text");
	}
//public static String MENU_FORMAT = _resourceBundle_CUA.getString("MnuFormat_text");
	public static String getMniFormatFontText() {
		return getResource("MnuFormatFont_text");
	}
//public static String MENU_FORMAT_BG_COLOR = _resourceBundle_CUA.getString("MnuFormatBackgroundColor_text");
	public static String getMniFormatLineColorText() {
		return getResource("MnuFormatLineColor_text");
	}
//public static String MENU_OPEN_HELP = _resourceBundle_CUA.getString("MniHelp_text");
	public static String getMniHelpAboutText() {
		return getResource("MniHelpAbout_text");
	}
//public static String MENU_HELP = _resourceBundle_CUA.getString("MnuHelp_text");
	public static String getMniHelpText() {
		return getResource("MniHelp_text");
	}
//public static String MENU_WINDOW_ORDER_AUTOMATICALLY = _resourceBundle_CUA.getString("MnuWindowOrderAutomatically");
	public static String getMniWindowCascadeText() {
		return getResource("MniWindowCascade_text");
	}
//public static String MENU_WINDOW = _resourceBundle_CUA.getString("MnuWindow_text");
	public static String getMniWindowOrderAutomaticallyText() {
		return getResource("MnuWindowOrderAutomatically");
	}
//public static String MENU_WINDOW_CASCADE = _resourceBundle_CUA.getString("MniWindowCascade_text");
	public static String getMniWindowTileText() {
		return getResource("MniWindowTile_text");
	}
//public static String MENU_FILE_EXPORT = _resourceBundle_CUA.getString("MniFileExport_text");

	// EDIT -------------------------------------------
	public static String getMnuEditText() {
		return getResource("MnuEdit_text");
	}
//public static String MENU_WINDOW_TILE = _resourceBundle_CUA.getString("MniWindowTile_text");

	// EXTRAS -------------------------------------------
	public static String getMnuExtrasText() {
		return getResource("MnuExtras_text");
	}
//private static java.util.ResourceBundle _resourceBundle_CUA = ch.ehi.basics.i18n.ResourceBundle.getBundle(CommonUserAccess.class);

	// FILE -------------------------------------------
	public static String getMnuFileText() {
		return getResource("MnuFile_text");
	}
//public static String MENU_VIEW_STATUSBAR = _resourceBundle_CUA.getString("MniViewStatusbar_text");

	// FORMAT -------------------------------------------
	public static String getMnuFormatText() {
		return getResource("MnuFormat_text");
	}
//public static String MENU_REPORTS = _resourceBundle_CUA.getString("MnuReports_text");

	// HELP -------------------------------------------
	public static String getMnuHelpText() {
		return getResource("MnuHelp_text");
	}
//public static String MENU_EXTRAS_OPTIONS_WINDOW = _resourceBundle_CUA.getString("MniExtrasOptionsWindow_text");

	
	public static String getMnuLanguageText() {
		return getResource("MnuLanguage_text");
	}
//public static String MENU_TOOLS = _resourceBundle_CUA.getString("MnuTools_text");
	
	// REPORTS -------------------------------------------
	public static String getMnuReportsText() {
		return getResource("MnuReports_text");
	}
//public static String MENU_FORMAT_FILL_COLOR = _resourceBundle_CUA.getString("MnuFormatFillColor_text");

	// TOOLS -------------------------------------------
	public static String getMnuToolsText() {
		return getResource("MnuTools_text");
	}
//public static String MENU_VIEW = _resourceBundle_CUA.getString("MnuView_text");
	public static String getMnuViewLookAndFeelText() {
		return getResource("MnuViewLookAndFeel_text");
	}
//public static String MENU_VIEW_LOOK_FEEL = _resourceBundle_CUA.getString("MnuViewLookAndFeel_text");
	public static String getMnuViewSymbollistsText() {
		return getResource("MnuViewSymbollists_text");
	}
//public static String MENU_EDIT_FIND_REPLACE_WINDOW = _resourceBundle_CUA.getString("MniEditFindReplaceWindow_text");

	// VIEW -------------------------------------------
	public static String getMnuViewText() {
		return getResource("MnuView_text");
	}
//public static String MENU_HELP_ABOUT = _resourceBundle_CUA.getString("MniHelpAbout_text");

	// WINDOW -------------------------------------------
	public static String getMnuWindowText() {
		return getResource("MnuWindow_text");
	}
//public static String CTSAVE_CHANGES = _resourceBundle_CUA.getString("CTSaveChanges");
	public static String getQuestionSaveChanges() {
		return getResource("CQSaveChanges");
	}
//public static String CTFILE_OPEN = _resourceBundle_CUA.getString("CTFileOpen"); 
	
	
	
	/**
	 * Return an NLS-Resource.
	 * @param property
	 * @return
	 */
	private static String getResource(String property) {
		return ch.softenvironment.client.ResourceManager.getInstance().getResource(CommonUserAccess.class, property);
	}
//public static String CQSAVE_CHANGES = _resourceBundle_CUA.getString("CQSaveChanges");
	public static String getTitleFileOpen() {
		return getResource("CTFileOpen");
	}
	// flat Strings
	public static String getTitleSaveChanges() {
		return getResource("CTSaveChanges");
	}
}
