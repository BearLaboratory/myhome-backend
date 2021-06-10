package pro.dengyi.myhome.servicedevice;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DengYi
 * @version v1.0
 */
@SpringBootTest
public class JsTest {

    /**
     * 测试初始化引擎
     *
     * @throws ScriptException
     */
    @Test
    public void initEngine() throws ScriptException {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("javascript");
        String script = "print ('这是什么？')";
        scriptEngine.eval(script);
    }

    /**
     * 测试在引擎中判断
     *
     * @throws ScriptException
     */
    @Test
    public void ifDemo() throws ScriptException {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("javascript");
        String script = "if(device.id===1){print(device)}";
        Map<String, Object> param = new HashMap<>();
        param.put("id", 1);
        scriptEngine.put("device", param);
        scriptEngine.eval(script);
    }

    /**
     * 测试返回值
     *
     * @throws ScriptException
     */
    @Test
    public void returnDemo() throws ScriptException {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("javascript");
        String script = "if(device.id===1){device.name='hello' }";
        Map<String, Object> param = new HashMap<>();
        param.put("id", 1);
        scriptEngine.put("device", param);
        scriptEngine.eval(script);
        Map<String, Object> device = (Map<String, Object>) scriptEngine.get("device");
        System.out.println(device.get("name"));
    }


}
