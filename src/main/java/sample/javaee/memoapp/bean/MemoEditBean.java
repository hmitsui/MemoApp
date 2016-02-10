/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.memoapp.bean;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import sample.javaee.memoapp.ejb.MemoFacade;
import sample.javaee.memoapp.entity.Memo;

/**
 * メモアプリ編集画面の管理Beanクラス
 * 
 * 登録済みのメモの編集を行う
 *
 * @author
 */
@Named(value = "memoEditBean")
@ViewScoped
public class MemoEditBean {

    /**
     * 編集するメモのエンティティ
     */
    @Getter
    private Memo editMemo;
    
    /**
     * メモのデータベースアクセス用EJB
     */
    @Inject
    private MemoFacade memoFacade;
    
    /**
     * 画面初期化処理
     */
    @PostConstruct
    public void init(){
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        Integer key = (Integer) flash.get("editMemoId");
        editMemo = memoFacade.find(key);
    }
    
    /**
     * メモの更新
     */
    public void updateMemo(){
        memoFacade.edit(editMemo);
    }
    
}
