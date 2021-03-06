package com.ec.item.web;

import com.ec.item.service.SpecService;
import com.lh.ec.common.exception.EcException;
import com.lh.ec.item.pojo.SpecGroup;
import com.lh.ec.item.pojo.SpecParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("spec")
public class SpecController {

    @Autowired
    private SpecService specService;

    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> findSpecGrpByCId(@PathVariable("cid") Long cid){

        return ResponseEntity.ok(specService.findByCId(cid));
    }



    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> querySpecParams(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching,
            @RequestParam(value = "generic", required = false) Boolean generic
    ) {
        return ResponseEntity.ok(specService.querySpecParams(gid, cid, searching, generic));
    }

    @PostMapping("param")
    public ResponseEntity<Void> createSpecParam(@RequestBody SpecParam specParam){
        specService.saveSpecParam(specParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("param")
    public ResponseEntity<Void> updateSpecParam(@RequestBody SpecParam specParam){
        specService.saveSpecParam(specParam);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("param/{id}")
    public  ResponseEntity<Void> deleteSpecParam(@PathVariable("id") Long id) {
        try {
            specService.deleteSpecParam(id);
        } catch (EcException e) {
            log.info(e.getExceptionEnum().getMsg());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("{cid}")
    public ResponseEntity<List<SpecGroup>> findSpecsByCid(@PathVariable("cid") Long cid) {
        //todo
        return ResponseEntity.ok(specService.findSpecsByCid(cid));
    }
}
