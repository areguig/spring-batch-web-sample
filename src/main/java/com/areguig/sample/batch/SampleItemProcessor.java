package com.areguig.sample.batch;

import com.areguig.sample.bean.SampleItem;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by akli on 17/08/15.
 */
public class SampleItemProcessor implements ItemProcessor<SampleItem, SampleItem> {

	@Override
	public SampleItem process(SampleItem sampleItem) throws Exception {
		sampleItem.setItemValue("TRANSFORMED "+sampleItem.getItemValue());
		return sampleItem;
	}
}
