package com.example.demo.Repository;

import com.example.demo.Model.Extras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ExtrasRepository {
    @Autowired
    JdbcTemplate template;
    public List<Extras> fetchAll()
    {
        String sql = "SELECT * FROM extras";
        RowMapper<Extras> rowMapper=new BeanPropertyRowMapper<>(Extras.class);
        return template.query(sql,rowMapper);
    }
    public Extras addExtras(Extras ex)
    {
        String sql= "INSERT INTO extras VALUES(DEFAULT,?,?,?,?)";
        template.update(sql,ex.getName_extra(),ex.getPrice(),ex.getAvailable(),ex.getExtra_reservation());
        return null;
    }
    public Extras findExtraByID(int id)
    {
        String sql="SELECT * FROM extras WHERE extra_id =?";
        RowMapper<Extras>rowMapper=new BeanPropertyRowMapper<>(Extras.class);
        return template.queryForObject(sql,rowMapper,id);
    }
    public void deleteExtra(int id)
    {
        String sql= "DELETE FROM extras WHERE extra_id = ?";
        template.update(sql,id);

    }
    public void updateExtra(Extras ex,int id)
    {
        String sql="UPDATE extras SET name_extra = ? ,price = ?, available= ? WHERE extra_id = ?;";
        template.update(sql,ex.getName_extra(),ex.getPrice(),ex.getAvailable(),id);
    }
}
