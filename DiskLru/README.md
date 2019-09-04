DiskLruCache.open() -->DiskLruCache.readLru()		-->DiskLruCache.readLruLine() //basically we check the entry value here
																					EntryInLru entry = lruEntries.get(key);-->EntryInLru.Editor-->Editor.EntryInLru
																					if (entry == null) {
																					  entry = new EntryInLru(key);
																					  lruEntries.put(key, entry);
																					}
													-->DiskLruCache.rebuildLru()
													-->Util.closeQuietly(reader)
													
					-->DiskLruCache.processLru()	-->DiskLruCache.deleteIfExists()
