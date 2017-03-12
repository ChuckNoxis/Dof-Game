package org.starloco.locos.database.statics.data;

import com.zaxxer.hikari.HikariDataSource;
import org.starloco.locos.game.area.Area;
import org.starloco.locos.database.statics.AbstractDAO;
import org.starloco.locos.game.world.world.World;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaData extends AbstractDAO<Area>
{
	public AreaData(HikariDataSource dataSource)
	{
		super(dataSource);
	}

	@Override
	public void load(Object obj) {}

	@Override
	public boolean update(Area area)
	{
		return false;
	}

	public void load() {
		Result result = null;
		try {
			result = getData("SELECT * from area_data");
			ResultSet RS = result.resultSet;
			while (RS.next()) {
				Area A = new Area(RS.getInt("id"), RS.getInt("superarea"));
				World.world.addArea(A);
			}
		} catch (SQLException e) {
			super.sendError("Area_dataData load", e);
		} finally	{
			close(result);
		}
	}
}
