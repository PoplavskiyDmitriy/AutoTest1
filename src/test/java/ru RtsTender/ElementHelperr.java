package ru.mail.dimon1985;

public class ElementHelperr {
    private static String dateStartField = "BaseMainContent_MainContent_txtPublicationDate_txtDateFrom";
    private static String dateEndField = "BaseMainContent_MainContent_txtPublicationDate_txtDateTo";
    private static String checkBox223FZ = "BaseMainContent_MainContent_chkPurchaseType_0";
    private static String checkBoxCommercialPurchase = "BaseMainContent_MainContent_chkPurchaseType_1";
    private static String priceRangeFrom = "BaseMainContent_MainContent_txtStartPrice_txtRangeFrom";
    private static String buttonSearch = "BaseMainContent_MainContent_btnSearch";
    private static String pageCount = "sp_1_BaseMainContent_MainContent_jqgTrade_toppager";
    private static String EISNumberColumn = "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_OosNumber']";
    private static String priceColumn = "//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_StartPrice']";
    private static String nextPage = "next_t_BaseMainContent_MainContent_jqgTrade_toppager";
    private static String pageNumber = "//*[@id=\"BaseMainContent_MainContent_jqgTrade_toppager_center\"]/table/tbody/tr/td[4]/input";
    private static String canceled = "#lotStateTabs > ul > li:nth-child(11) > a";

    public static String getDateStartField() {
        return dateStartField;
    }

    public static String getDateEndField() {
        return dateEndField;
    }

    public static String getCheckBox223FZ() {
        return checkBox223FZ;
    }

    public static String getCheckBoxCommercialPurchase() {
        return checkBoxCommercialPurchase;
    }

    public static String getPriceRangeFrom() {
        return priceRangeFrom;
    }

    public static String getButtonSearch() {
        return buttonSearch;
    }

    public static String getPageCount() {
        return pageCount;
    }

    public static String getEISNumberColumn() {
        return EISNumberColumn;
    }

    public static String getPriceColumn() {
        return priceColumn;
    }

    public static String getNextPage() {
        return nextPage;
    }

    public static String getPageNumber() {
        return pageNumber;
    }

    public static String getCanceled() {
        return canceled;
    }
}
