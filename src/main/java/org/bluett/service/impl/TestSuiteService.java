package org.bluett.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.entity.SystemCache;
import org.bluett.entity.pojo.TestSuite;
import org.bluett.mapper.TestSuiteMapper;
import org.bluett.service.ITestSuiteService;
import org.bluett.util.DBUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TestSuiteService implements ITestSuiteService {
    private static final Logger log = LogManager.getLogger(TestSuiteService.class);

    private static final String FILE_NAME = "testSuite.json";
    private static final File TEST_SUITE_FILE = SystemCache.CACHE_PATH.resolve(FILE_NAME).toFile();

    @Override
    public boolean save(List<TestSuite> testSuiteList) {
        if(TEST_SUITE_FILE.exists()) return update(testSuiteList);
        try {
            File file = FileUtil.touch(TEST_SUITE_FILE);
            String jsonStr = JSONUtil.toJsonStr(testSuiteList);
            FileUtil.writeUtf8String(jsonStr, file);
        } catch (Exception e) {
            log.error("Save test suite list failed", e);
            return false;
        }
        return false;
    }

    @Override
    public boolean save(TestSuite testSuite) {
        if(TEST_SUITE_FILE.exists()) return update(testSuite);
        try {
            File file = FileUtil.touch(TEST_SUITE_FILE);
            JSONArray jsonArray = JSONUtil.createArray();
            jsonArray.add(JSONUtil.parseObj(testSuite));
            String jsonStr = jsonArray.toString();
            FileUtil.writeUtf8String(jsonStr, file);
        } catch (Exception e) {
            log.error("Save test suite failed", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(TestSuite testSuite) {
        if(!TEST_SUITE_FILE.exists()) return save(testSuite);
        try {
            JSONArray jsonArray = JSONUtil.readJSONArray(TEST_SUITE_FILE, CharsetUtil.CHARSET_UTF_8);
            for (int i = 0; i < jsonArray.size(); i++) {
                if(jsonArray.getJSONObject(i).getStr("name").equals(testSuite.getName())){
                    jsonArray.set(i, JSONUtil.parseObj(testSuite));
                    FileUtil.writeUtf8String(jsonArray.toString(), TEST_SUITE_FILE);
                    return true;
                }
            }
            jsonArray.add(JSONUtil.parseObj(testSuite));
            FileUtil.writeUtf8String(jsonArray.toString(), TEST_SUITE_FILE);
        } catch (Exception e) {
            log.error("Update test suite failed", e);
            return false;
        }
        return false;
    }

    @Override
    public boolean update(List<TestSuite> testSuiteList) {
        if(!TEST_SUITE_FILE.exists()) return save(testSuiteList);
        try {
            JSONArray jsonArray = JSONUtil.readJSONArray(TEST_SUITE_FILE, CharsetUtil.CHARSET_UTF_8);
            for (TestSuite testSuite : testSuiteList) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    if(jsonArray.getJSONObject(i).getStr("name").equals(testSuite.getName())){
                        jsonArray.set(i, JSONUtil.parseObj(testSuite));
                        break;
                    }
                }
            }
            FileUtil.writeUtf8String(jsonArray.toString(), TEST_SUITE_FILE);
        } catch (Exception e) {
            log.error("Update test suite list failed", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(TestSuite testSuite) {
        if(!TEST_SUITE_FILE.exists()) return false;
        try {
            JSONArray jsonArray = JSONUtil.readJSONArray(TEST_SUITE_FILE, CharsetUtil.CHARSET_UTF_8);
            for (int i = 0; i < jsonArray.size(); i++) {
                if(jsonArray.getJSONObject(i).getStr("name").equals(testSuite.getName())){
                    jsonArray.remove(i);
                    FileUtil.writeUtf8String(jsonArray.toString(), TEST_SUITE_FILE);
                    return true;
                }
            }
        } catch (Exception e) {
            log.error("Delete test suite failed", e);
            return false;
        }
        return false;
    }

    @Override
    public boolean delete(List<TestSuite> testSuiteList) {
        if(!TEST_SUITE_FILE.exists()) return false;
        try {
            JSONArray jsonArray = JSONUtil.readJSONArray(TEST_SUITE_FILE, CharsetUtil.CHARSET_UTF_8);
            for (TestSuite testSuite : testSuiteList) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    if(jsonArray.getJSONObject(i).getStr("name").equals(testSuite.getName())){
                        jsonArray.remove(i);
                        break;
                    }
                }
            }
            FileUtil.writeUtf8String(jsonArray.toString(), TEST_SUITE_FILE);
        } catch (Exception e) {
            log.error("Delete test suite list failed", e);
            return false;
        }
        return true;
    }

    @Override
    public List<TestSuite> getTestSuiteList() {
        if(!TEST_SUITE_FILE.exists()) return new ArrayList<>();
        try {
            return JSONUtil.toList(JSONUtil.readJSONArray(TEST_SUITE_FILE, CharsetUtil.CHARSET_UTF_8), TestSuite.class);
        } catch (Exception e) {
            log.error("Get test suite list failed", e);
        }
        return new ArrayList<>();
    }

    @Override
    public TestSuite selectTestSuite(Integer id) {
        try(SqlSession session = DBUtil.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            return testSuiteMapper.selectTestSuite(1);
        }catch (Exception e){
            log.error(e);
        }
        return null;
    }
}
