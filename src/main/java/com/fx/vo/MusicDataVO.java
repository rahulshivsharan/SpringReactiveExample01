package com.fx.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MusicDataVO implements Serializable{
	private Map<String,List<Map<String,SongVO>>> tracks;
	private Map<String, List<Map<String,SingerVO>>> artists;
	
	public Map<String, List<Map<String, SongVO>>> getTracks() {
		return tracks;
	}
	public void setTracks(Map<String, List<Map<String, SongVO>>> tracks) {
		this.tracks = tracks;
	}
	public Map<String, List<Map<String, SingerVO>>> getArtists() {
		return artists;
	}
	public void setArtists(Map<String, List<Map<String, SingerVO>>> artists) {
		this.artists = artists;
	}



}
