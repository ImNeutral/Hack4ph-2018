<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class ProductPosting extends Model
{
    protected $table = "product_postings";

    public function postType() {
        return $this->belongsTo('App\SystemCode', 'id');
    }
}
