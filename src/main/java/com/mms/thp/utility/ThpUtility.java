package com.mms.thp.utility;

import org.springframework.ui.Model;

public class ThpUtility {

    public static final String ADMIN_KEY = "NO-ONE-CAN-CHANGE";
    public static final int RECORD_PER_PAGE = 15;
    private static final String TOTAL_PAGES_ATTR_NAME = "totalPages";
    private static final String NEXT_PAGE_LINK_ATTR_NAME = "nextPageLink";
    private static final String PREVIOUS_PAGE_LINK_ATTR_NAME = "previousPageLink";
    private static final String HAS_NEXT_PAGE_ATTR_NAME = "hasNextPage";
    private static final String HAS_PREVIOUS_PAGE_ATTR_NAME = "hasPreviousPage";
    private static final String CURRENT_PAGE_ATTR_NAME = "currentPage";
    private static final String PAGE_NO_REQUEST_PARAM_NAME = "pageNo";

    private ThpUtility(){/* No constructor for utility class */}
    public static String normalizeString(String arg) {
        return arg.trim().replaceAll("\\s", "").toLowerCase();
    }
    public static String normalizeStringForDisplaying(String arg) {
        return arg.trim().toUpperCase();
    }
    public static void populateModelForPagination(Model model, long totalMedicineCount, int pageNo, String url){

        double totalPages = (double)totalMedicineCount/RECORD_PER_PAGE;
        int totalIntCount = (int)totalMedicineCount/RECORD_PER_PAGE;
        if(totalPages > totalIntCount)
            totalPages+=1;
        boolean hasNextPage = false;
        boolean hasPreviousPage = false;
        String nextPageLink = null;
        String previousPageLink = null;
        int currentPage = 0;
        {
            // Pagination logic
            int pageNoTemp = pageNo+1;
            currentPage = pageNoTemp;
            if((int)totalPages > pageNoTemp){
                hasNextPage = true;
                nextPageLink = url+ PAGE_NO_REQUEST_PARAM_NAME + "="+pageNoTemp;
                previousPageLink = url+ PAGE_NO_REQUEST_PARAM_NAME + "="+pageNo;
            }
            if(pageNo > 0) {
                hasPreviousPage = true;
                previousPageLink = url+ PAGE_NO_REQUEST_PARAM_NAME + "="+(pageNo-1);
            }
            if((int)totalPages == pageNoTemp) {
                hasNextPage = false;
            }
        }
        model.addAttribute(TOTAL_PAGES_ATTR_NAME, (int)totalPages);
        model.addAttribute(NEXT_PAGE_LINK_ATTR_NAME, nextPageLink);
        model.addAttribute(PREVIOUS_PAGE_LINK_ATTR_NAME, previousPageLink);
        model.addAttribute(HAS_NEXT_PAGE_ATTR_NAME, hasNextPage);
        model.addAttribute(HAS_PREVIOUS_PAGE_ATTR_NAME, hasPreviousPage);
        model.addAttribute(CURRENT_PAGE_ATTR_NAME, currentPage);

    }
    public enum MedicineColumnIndex {
        NAME,
        COMPANY,
        PRICE,
        COUNT,
        ML,
        BOX_NUMBER
    }
}
