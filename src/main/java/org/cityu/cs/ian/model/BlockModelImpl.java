package org.cityu.cs.ian.model;

import org.apache.commons.io.FileUtils;
import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.util.JsonUtil;
import org.cityu.cs.ian.util.PropertiesUtil;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class BlockModelImpl implements IBlockModel{
    /**
     * 把区块存成文件保存到本地
     * @param blockJson
     */
    public boolean saveBlockToLocal(String blockJson) {
        String rootPath = getBlockPath();
        File file = new File(rootPath + "/" + getCurrentBlockName());
        try {
            FileUtils.write(file,blockJson,"utf-8");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getBlockPath() {
        return PropertiesUtil.readValue("config.properties","block.localPath");
    }

    /**
     * 获取新区块文件名称  暂定使用区块高度命名
     * @return
     */
    public int getCurrentBlockName() {
        return getTotalBlockCount() + 1;
    }




    /**
     * 获取当前链的最后一个区块的hash
     * @return
     */
    public String getTopBlockHash() {
        File file=null;
        List<File> files = getAllBolckFiles();
        if(files!=null&files.size()>0){
            file = files.get(files.size() - 1);
        }
        try {
            String json = FileUtils.readFileToString(file, "utf-8");
            BlockBean blockBean = JsonUtil.fromJson(json, BlockBean.class);
            return blockBean.getBlockHeader().getBlockHash();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前链的高度
     *
     * @return
     */
    public int getTotalBlockCount() {
        List<File> files = getAllBolckFiles();
        if(files!=null&files.size()>0)
            System.out.println(files.get(files.size()-1));
        return files.size();
    }

    /**
     * 获取所有区块文件
     * @return
     */
    public List<File> getAllBolckFiles() {
        return (List<File>) FileUtils.listFiles(new File(getBlockPath()),null,true);
    }
}
