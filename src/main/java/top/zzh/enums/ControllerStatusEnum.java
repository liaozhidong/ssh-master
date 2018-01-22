package top.zzh.enums;

public enum  ControllerStatusEnum {

    CLASS_SAVE_SUCCESS(100, "success", "班级添加成功"),
    CLASS_SAVE_FAIL(101, "fail", "班级添加失败，稍候再试"),
    CLASS_REMOVE_SUCCESS(102, "success", "班级删除成功"),
    CLASS_REMOVE_FAIL(103, "fail", "班级删除失败，稍候再试"),
    CLASS_UPDATE_SUCCESS(104, "success", "班级更新成功"),
    CLASS_UPDATE_FAIL(105, "fail", "班级更新失败，稍候再试");

    private Integer code;
    private String result;
    private String message;

    ControllerStatusEnum(Integer code, String result, String message) {
        this.code = code;
        this.result = result;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
