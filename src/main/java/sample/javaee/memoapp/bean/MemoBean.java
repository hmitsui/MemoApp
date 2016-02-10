/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.memoapp.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import sample.javaee.memoapp.ejb.MemoFacade;
import sample.javaee.memoapp.entity.Memo;


/**
 * メモアプリメイン画面の管理Beanクラス
 * 
 * メモの新規追加と一覧表示を行う
 *
 * @author
 */
@Named(value = "memoBean")
@ViewScoped
public class MemoBean implements Serializable{

    /**
     * 入力されたメモ
     */
    @Getter @Setter
    private String memo;
    
    /**
     * 一覧表示するメモのリスト
     */
    @Getter @Setter
    private List<Memo> memoList;
    
    /**
     * メモのデータベース・アクセス要EJB
     */
    @Inject
    private MemoFacade memoFacade;

    /**
     * 画面初期化処理
     */
    @PostConstruct
    public void init(){
        getAllMemo();
    }

    /**
     * メモの新規登録
     */
    public void createMemo(){
        if(memo.length() > 0){
            Memo newMemo = new Memo();
            newMemo.setMemo(memo);
            memoFacade.create(newMemo);
            getAllMemo();
            }
    }

    /**
     * メモの更新
     * @param editMemo 更新するメモ
     * @return 画面遷移先のパス
     */
    public String updateMemo(Memo editMemo){
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("editMemoId", editMemo.getId());
        return "memoEdit.xhtml";
    }
    
    /**
     * メモの削除
     * @param delMemo 削除するメモ
     */
    public void deleteMemo(Memo delMemo){
        memoFacade.remove(delMemo);
        getAllMemo();
    }

    /**
     * 登録されたすべてのメモを取得
     */
    private void getAllMemo(){
        memoList = memoFacade.findAll();
    }
    
}
