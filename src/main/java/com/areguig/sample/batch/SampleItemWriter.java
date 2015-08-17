package com.areguig.sample.batch;

import com.areguig.sample.bean.SampleItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.Iterator;
import java.util.List;

/**
 * Created by akli on 17/08/15.
 */
public class SampleItemWriter implements ItemWriter<SampleItem> {

	private static final Logger log = LoggerFactory
			.getLogger(SampleItemWriter.class);
	@Override
	public void write(List<? extends SampleItem> list) throws Exception {
		if (!list.isEmpty()) {
			log.info("Writer : "+ list.size()+" items");
			Iterator i$ = list.iterator();
			while (i$.hasNext()) {
				SampleItem item = (SampleItem) i$.next();
				log.info("Writing item with value "+item.getItemValue());

			}
			log.info("Done writing items.");
		}

	}
}
