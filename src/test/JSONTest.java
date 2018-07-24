package test;


import org.json.JSONException;
import org.json.JSONObject;

public class JSONTest {
    public static void main(String[] args) {
        new JSONTest().parseJson();
    }

    private void parseJson() {
        JSONObject object = null;
        try {
            String data = RESPONSE_DATA;
            object = new JSONObject(data);
            System.out.println(object.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(object.toString());
    }

    private static final  String RESPONSE_DATA = "{\n" +
            "    \"title\": \"VacationRegression (08/14/2013 - 08/14/2013): Regression User \",\n" +
            "    \"category\": \"HRIS_EMP_CHANGE_REQUESTS\",\n" +
            "    \"key_details\": [\n" +
            "        {\n" +
            "            \"key_detail_name\": \"profile_id\",\n" +
            "            \"type\": \"user\",\n" +
            "            \"label\": \"Employee\",\n" +
            "            \"profile_id\": \"c,PLTMobSWTO,reg1\",\n" +
            "            \"full_name\": \"Regression User\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"key_detail_name\": \"status\",\n" +
            "            \"type\": \"text\",\n" +
            "            \"label\": \"Status\",\n" +
            "            \"value\": \"COMPLETED\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"details\": [\n" +
            "        {\n" +
            "            \"label\": \"Employee Time\",\n" +
            "            \"detail_list\": [\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"hris_finduser\",\n" +
            "                    \"value\": \"Regression User\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"User\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"picklistautocomplete\",\n" +
            "                    \"value\": \"false\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Undetermined End Date\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"decimal\",\n" +
            "                    \"value\": \"1\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Deduction Quantity\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"picklistautocomplete\",\n" +
            "                    \"value\": \"false\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Flexible Requesting\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"foundation_autocomplete\",\n" +
            "                    \"value\": \"VacationRegression (VacationRegression)\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Time Type\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"date\",\n" +
            "                    \"value\": \"2013-08-14\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Start Date\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"date\",\n" +
            "                    \"value\": \"2013-08-14\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"End Date\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"picklistautocomplete\",\n" +
            "                    \"value\": \"CANCELLED\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Approval Status\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"decimal\",\n" +
            "                    \"value\": \"1\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Number Of Days\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"decimal\",\n" +
            "                    \"value\": \"8\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Number Of Hours\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"number\",\n" +
            "                    \"value\": \"4,382\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Workflow Request\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"textarea\",\n" +
            "                    \"value\": \"commnets\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Comment\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"picklistautocomplete\",\n" +
            "                    \"value\": \"true\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Editable\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"string\",\n" +
            "                    \"value\": \"2b5484052e3c4f6a93441afe846fcb87\",\n" +
            "                    \"old_value\": \"string\",\n" +
            "                    \"label\": \"External Code\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"attachment\",\n" +
            "                    \"label\": \"medicine bill\",\n" +
            "                    \"url\": \"/api/v4/ec/workflows/attachment\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"picklistautocomplete\",\n" +
            "                    \"value\": \"false\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Workflow Initiated By Admin\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"date\",\n" +
            "                    \"value\": \"2013-08-14\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Date\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"decimal\",\n" +
            "                    \"value\": \"1\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Deduction Quantity\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"decimal\",\n" +
            "                    \"value\": \"1\",\n" +
            "                    \"old_value\": \"\",\n" +
            "                    \"label\": \"Number Of Days\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"decimal\",\n" +
            "                    \"value\": \"8\",\n" +
            "                    \"old_value\": \"old_value\",\n" +
            "                    \"label\": \"Number Of Hours\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"string\",\n" +
            "                    \"value\": \"3eda33417df14323bd550afb91150d92\",\n" +
            "                    \"old_value\": \"old_value\",\n" +
            "                    \"label\": \"External Code\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"detail_name\": \"change\",\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"value\": \"[]\",\n" +
            "                    \"old_value\": \"old_value\",\n" +
            "                    \"label\": \"timeCalendar.timeAccountDetail\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ],\n" +
            "    \"activities\": [\n" +
            "        {\n" +
            "            \"activity_name\": \"initiated\",\n" +
            "            \"label\": \"Initiated by\",\n" +
            "            \"profile_id\": \"c,PLTMobSWTO,reg1\",\n" +
            "            \"full_name\": \"Regression User\",\n" +
            "            \"formatted_date\": \"July 25, 2013\",\n" +
            "            \"date\": 1374807307000,\n" +
            "            \"comment\": \"commnets\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"activity_name\": \"approved\",\n" +
            "            \"label\": \"Approved by\",\n" +
            "            \"profile_id\": \"c,PLTMobSWTO,admin\",\n" +
            "            \"full_name\": \"Admin Shi\",\n" +
            "            \"formatted_date\": \"September 30, 2014\",\n" +
            "            \"date\": 1412060591000,\n" +
            "            \"comment\": null\n" +
            "        }\n" +
            "    ],\n" +
            "    \"actions\": {\n" +
            "      \"action_list\": [\n" +
            "          {\n" +
            "              \"action_name\": \"next_step\",\n" +
            "              \"label\": \"Approve\",\n" +
            "              \"comment\": \"optional\",\n" +
            "              \"request\": {\n" +
            "                  \"url\": \"/api/v5/recruiting/requisitions/7178/next_step\",\n" +
            "                  \"method\": \"POST\",\n" +
            "                  \"body\": {\n" +
            "                      \"jobReqId\": \"5842\",\n" +
            "                      \"formDataId\": \"7178\"\n" +
            "                  }\n" +
            "              },\n" +
            "              \"color\": \"positive\",\n" +
            "              \"user_pick_list\": [\n" +
            "                  {\n" +
            "                      \"delegatee_profile_id\": \"c,PLTShruthi,msampson1\",\n" +
            "                      \"full_name\": \"Mandy Sampson\"\n" +
            "                  }\n" +
            "              ],\n" +
            "              \"popup_label\": \"Next step\"\n" +
            "          },\n" +
            "          {\n" +
            "              \"action_name\": \"send_to\",\n" +
            "              \"label\": \"Send to\",\n" +
            "              \"comment\": \"simple\",\n" +
            "              \"request\": {\n" +
            "                  \"url\": \"/api/v5/recruiting/requisitions/7178/send_to\",\n" +
            "                  \"method\": \"POST\",\n" +
            "                  \"body\": {\n" +
            "                      \"jobReqId\": \"5842\",\n" +
            "                      \"formDataId\": \"7178\"\n" +
            "                  }\n" +
            "              },\n" +
            "              \"color\": \"neutral\",\n" +
            "              \"user_pick_list\": [\n" +
            "                  {\n" +
            "                      \"delegatee_profile_id\": \"c,PLTShruthi,msampson1\",\n" +
            "                      \"full_name\": \"Mandy Sampson\"\n" +
            "                  }\n" +
            "              ],\n" +
            "              \"popup_label\": \"Send to\"\n" +
            "          }\n" +
            "      ]\n" +
            "    }\n" +
            "}";
}
